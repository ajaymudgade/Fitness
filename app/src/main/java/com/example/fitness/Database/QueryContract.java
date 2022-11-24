package com.example.fitness.Database;

import com.example.fitness.Model.foodModel;
import com.example.fitness.Model.sleepModel;
import com.example.fitness.Model.tableRowCount;
import com.example.fitness.Model.userModel;
import com.example.fitness.Model.waterModel;
import com.example.fitness.Model.workoutModel;

import java.util.List;

import javax.security.auth.Subject;

public class QueryContract {

    public interface UserQuery {
        void createUser(userModel user, QueryResponse<Boolean> response);
        void readUser(String name, QueryResponse<userModel> response);
        void readAllUser(QueryResponse<List<userModel>> response);
        void updateUser(userModel user, QueryResponse<Boolean> response);
        void deleteUser(String name, QueryResponse<Boolean> response);
    }

    public interface FoodQuery {
        void createFood(foodModel food, QueryResponse<Boolean> response);
        void readAllFood(QueryResponse<List<foodModel>> response);
        void updateFood(foodModel food, QueryResponse<Boolean> response);
        void deleteFood(String userid, QueryResponse<Boolean> response);
    }

    public interface SleepQuery {
        void createSleep(sleepModel sleep, QueryResponse<Boolean> response);
        void readAllSleep(QueryResponse<List<sleepModel>> response);
        void updateSleep(sleepModel sleep, QueryResponse<Boolean> response);
        void deleteSleep(String userid, QueryResponse<Boolean> response);
    }

    public interface WaterQuery {
        void createWater(waterModel water, QueryResponse<Boolean> response);
        void readAllWater(QueryResponse<List<waterModel>> response);
        void updateWater(waterModel water, QueryResponse<Boolean> response);
        void deleteWater(String userid, QueryResponse<Boolean> response);
    }

    public interface WorkoutQuery {
        void createWorkout(workoutModel workout, QueryResponse<Boolean> response);
        void readAllWorkout(QueryResponse<List<workoutModel>> response);
        void updateWorkout(workoutModel workout, QueryResponse<Boolean> response);
        void deleteWorkout(String userid, QueryResponse<Boolean> response);
    }

    public interface TableRowCountQuery {
        void getTableRowCount(QueryResponse<tableRowCount> response);
    }
}
