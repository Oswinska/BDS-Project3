package vut.cz.bpcbdsproject3.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vut.cz.bpcbdsproject3.Postgre.AppBasicView;
import vut.cz.bpcbdsproject3.configuration.DataSourceConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppRepository
{
    private static final Logger logger = LoggerFactory.getLogger(AppRepository.class);

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

}
