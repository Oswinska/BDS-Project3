package vut.cz.bpcbdsproject3.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vut.cz.bpcbdsproject3.Postgre.*;
import vut.cz.bpcbdsproject3.configuration.DataSourceConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppRepository
{
    private static final Logger logger = LoggerFactory.getLogger(AppRepository.class);

    // Basic View
    public List<AppBasicView> getAllMovies()
    {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement prpstmt = connection.prepareStatement(
                     "SELECT f.film_id, f.film_name, f.pegi, f.air_time FROM film f ORDER BY f.film_id;"))
            {
                return mapToAppView(prpstmt, connection);
            } catch (SQLException e)
            {
                logger.error("Failed to get Movie DB\nMessage: " + e.getMessage());
            }
        return null;
    }

    private List<AppBasicView> mapToAppView(PreparedStatement prpstmt, Connection connection) throws SQLException
    {
        List<AppBasicView> view = new ArrayList<>();
        ResultSet rs = prpstmt.executeQuery();
        while (rs.next())
            {
                AppBasicView bv = new AppBasicView();
                bv.setId(rs.getLong("film_id"));
                bv.setName(rs.getString("film_name"));
                bv.setPegi(rs.getInt("pegi"));
                bv.setAirtime(rs.getString("air_time"));
                view.add(bv);
            }
        return view;
    }

    // Detailed View
    public AppDetailedView getDetailedView(Long film_id)
    {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement prpstmt = connection.prepareStatement("SELECT f.film_id, f.film_name, f.pegi, f.air_time, t.theatre_name,s.screen_number FROM  \"film\" f\n " +
                     "LEFT JOIN film_has_screen fs ON f.film_id = fs.film_id -- Right join if we want to filter out movies without a screen assigned\n " +
                     "LEFT JOIN screen s ON fs.screen_id = s.screen_id\n " +
                     "LEFT JOIN theatre t ON s.theatre_id = t.theatre_id\n " +
                     "WHERE f.film_id = ?;"))
            {
                prpstmt.setLong(1, film_id);
                try (ResultSet rs = prpstmt.executeQuery())
                    {
                        if (rs.next())
                            {
                                return mapToDetailedView(rs);
                            }
                    }
            } catch (SQLException e)
            {
                logger.error(String.format("Couldn't get Detailed view!\nMeassage: %s", e.getMessage()));
            }
        return null;
    }

    private AppDetailedView mapToDetailedView(ResultSet rs) throws SQLException
    {
        AppDetailedView view = new AppDetailedView();
        view.setId(rs.getLong("film_id"));
        view.setName(rs.getString("film_name"));
        view.setPegi(rs.getInt("pegi"));
        view.setAirTime(rs.getString("air_time"));
        view.setTheatre(rs.getString("theatre_name"));
        view.setScreen(rs.getInt("screen_number"));
        return view;
    }


    // Filtered View
    public List<AppBasicView> getFilteredMovies(Integer pegi)
    {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement prpstmt = connection.prepareStatement(
                     "SELECT f.film_id, f.film_name, f.pegi, f.air_time FROM film f " +
                             "WHERE f.pegi = ? ;"))
            {
                prpstmt.setInt(1, pegi);
                return mapToAppView(prpstmt, connection);
            } catch (SQLException e)
            {
                logger.error("Failed to get Filtered DB\nMessage: " + e.getMessage());
            }
        return null;
    }

    // Create Film
    public void createFilm(AppCreateView createView)
    {
        String insertFilm = "WITH ins AS (\n" +
                "INSERT INTO public.film (film_name, pegi, air_time)\n" +
                "VALUES (?,?,?)\n" +
                "RETURNING film_id),\n" +
                "ins2 AS (\n" +
                "INSERT INTO public.film_has_screen (film_id, screen_id)\n" +
                "VALUES ((SELECT film_id FROM ins),?))\n" +
                "\n" +
                "SELECT film_id FROM ins;";

        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement prpstmt = conn.prepareStatement(insertFilm, Statement.RETURN_GENERATED_KEYS))
            {
                prpstmt.setString(1, createView.getName());
                prpstmt.setInt(2, createView.getPegi());
                prpstmt.setTimestamp(3, createView.getAirTime());
                prpstmt.setLong(4, createView.getScreen());

                boolean affectedRows = prpstmt.execute();
                if (!affectedRows)
                    {
                        throw new SQLException("Creating film failed");
                    }
            } catch (SQLException e)
            {
                logger.error("Failed to add film\nMessage: " + e.getMessage());
            }
    }

    // edit Movie
    public void editMovie(AppEditView editView)
    {
        String editSQL = "begin;\n" +
                " UPDATE public.film f SET film_name = ? , pegi = ?, air_time = ? WHERE f.film_id = ?; \n" +
                " UPDATE public.film_has_screen fhs SET screen_id = ? WHERE fhs.film_id = ?; \n" +
                "commit;";
        String check = "SELECT film_name FROM public.film f WHERE f.film_id = ? ;";
        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement prpstmt = conn.prepareStatement(editSQL, Statement.RETURN_GENERATED_KEYS))
            {
                prpstmt.setString(1, editView.getName());
                prpstmt.setInt(2, editView.getPegi());
                prpstmt.setTimestamp(3, editView.getAirTime());
                prpstmt.setLong(4, editView.getId());
                prpstmt.setLong(5, editView.getScreen());
                prpstmt.setLong(6, editView.getId());
                try
                    {
                        conn.setAutoCommit(false);
                        try (PreparedStatement checkstatement = conn.prepareStatement(check, Statement.RETURN_GENERATED_KEYS))
                            {
                                checkstatement.setLong(1, editView.getId());
                                checkstatement.execute();
                            } catch (SQLException e)
                            {
                                throw new SQLException("This Movie doesn't exist");
                            }
                        int affectedRows = prpstmt.executeUpdate();
                        if (affectedRows == 0)
                            {
                                throw new SQLException("Updating Movie failed");
                            }
                        conn.commit();
                    } catch (SQLException e)
                    {
                        conn.rollback();
                    } finally
                    {
                        conn.setAutoCommit(true);
                    }
            } catch (SQLException e)
            {
                logger.error("Failed updating Movie");
            }
    }

    // Remove Movie
    public void removeFilm(Long id)
    {
        String remove = "DELETE FROM public.film WHERE film_id = ? ; " +
                "DELETE FROM public.film_has_screen WHERE film_id = ? ; ";
        try (Connection conn = DataSourceConfig.getConnection();
             PreparedStatement prpstmt = conn.prepareStatement(remove))
            {
                prpstmt.setLong(1, id);
                prpstmt.setLong(2, id);
                prpstmt.executeUpdate();
            } catch (SQLException e)
            {
                logger.error("Couldn't delete film\nMessage: " + e.getMessage());
            }
    }

    // Injection
    public List<InjectionView> getInjectionView(String input)
    {
        String injection = "SELECT id, first_name,last_name,nickname,email FROM \"injectionSQL\".person";
        try (Connection conn = DataSourceConfig.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(injection))
            {
                List<InjectionView> injectionViews = new ArrayList<>();
                while (rs.next())
                    {
                        injectionViews.add(mapToInjectionView(rs));
                    }
                return injectionViews;
            } catch (SQLException e)
            {
                logger.error("Failed to find users\nMessage:" + e.getMessage());
            }
        return null;
    }

    private InjectionView mapToInjectionView(ResultSet rs) throws SQLException
    {
        InjectionView injectionView = new InjectionView();
        injectionView.setId(rs.getLong("id"));
        injectionView.setFirstName(rs.getString("firstName"));
        injectionView.setLastName(rs.getString("lastName"));
        injectionView.setNickName(rs.getString("nickName"));
        injectionView.setEmail(rs.getString("email"));
        return injectionView;
    }
}
