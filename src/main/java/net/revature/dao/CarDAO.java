package net.revature.dao;

import net.revature.exception.person.PersonNotFoundException;
import net.revature.model.Car;
import net.revature.model.Person;
import net.revature.service.PersonService;
import net.revature.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO implements CarDAOInterface{
    PersonDAOInterface personDAO = new PersonDAO();
    @Override
    public List<Car> getAllCars() {
        try(Connection con = ConnectionUtil.getConnection()){
            List<Car> cars = new ArrayList<>();

            String sql = "SELECT * FROM car";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Car newCar = new Car(
                        rs.getInt("id"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getByte("passengers"),
                        rs.getString("color"),
                        personDAO.getPersonById(rs.getInt("owner_id"))
                );
                cars.add(newCar);
            }

            return cars;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Car getCarById(int id) {
        try(Connection con = ConnectionUtil.getConnection()){

            String sql = "SELECT * FROM car where id =?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Car car = new Car(
                        rs.getInt("id"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getByte("passengers"),
                        rs.getString("color"),
                        personDAO.getPersonById(rs.getInt("owner_id"))
                );
                return car;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Car> getCarsByOwner(Person carOwner) {
        try(Connection con = ConnectionUtil.getConnection()){
            List<Car> cars = new ArrayList<>();

            String sql = "SELECT * FROM car where owner_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,carOwner.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Car newCar = new Car(
                        rs.getInt("id"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getByte("passengers"),
                        rs.getString("color"),
                        carOwner
                );

                cars.add(newCar);
            }

            return cars;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Car updateCar(Car car) {
        return null;
    }

    @Override
    public Car addCar(Car car) {
        try(Connection con = ConnectionUtil.getConnection()){

            String sql = "insert into car(model,make,color,passengers) values(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1,car.getModel());
            ps.setString(2,car.getMake());
            ps.setString(3,car.getColor());
            ps.setInt(4,car.getPassengers());

            ps.executeUpdate();

            ResultSet keys_rs = ps.getGeneratedKeys();
            if(keys_rs.next()){
                int car_id = keys_rs.getInt("id");
                car.setId(car_id);
            }

            return car;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteCar(int car_id) {
        try(Connection con = ConnectionUtil.getConnection()) {

            String sql = "delete from car where id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,car_id);
            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Car assignOwner(Car car, Person owner) {
        try(Connection con = ConnectionUtil.getConnection()){

            String sql = "update car " +
                    "set owner_id = ? " +
                    "where id = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1,owner.getId());
            ps.setInt(2,car.getId());

            ps.executeUpdate();
            Car assignedCar = getCarById(car.getId());
            return assignedCar;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
