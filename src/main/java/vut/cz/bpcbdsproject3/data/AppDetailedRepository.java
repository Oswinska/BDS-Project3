package vut.cz.bpcbdsproject3.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vut.cz.bpcbdsproject3.Postgre.AppDetailedView;
import vut.cz.bpcbdsproject3.configuration.DataSourceConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppDetailedRepository
{
    private static final Logger logger = LoggerFactory.getLogger(AppRepository.class);


    public AppDetailedView getDetailedView(Long film_id)
    {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement prpstmt = connection.prepareStatement("SELECT f.film_id, f.film_name, f.pegi, f.air_time, t.theatre_name,s.screen_number FROM  \"film\" f\n" +
                     "LEFT JOIN film_has_screen fs ON f.film_id = fs.film_id -- Right join if we want to filter out movies without a screen assigned\n" +
                     "LEFT JOIN screen s ON fs.screen_id = s.screen_id\n" +
                     "LEFT JOIN theatre t ON s.theatre_id = t.theatre_id\n" +
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
        view.setFilmId(rs.getLong("film_id"));
        view.setFilmName(rs.getString("film_id"));
        view.setPegi(rs.getInt("film_id"));
        view.setAirTime(rs.getString("film_id"));
        view.setTheatre(rs.getString("film_id"));
        view.setScreen(rs.getInt("film_id"));
        return view;
    }
}
