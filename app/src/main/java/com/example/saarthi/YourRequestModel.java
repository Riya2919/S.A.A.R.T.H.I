package com.example.saarthi;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class YourRequestModel {
    @SerializedName("input1")
    private List<InputData> input1;

    public YourRequestModel(int crime, int peopleInvolved, int place) {
        input1 = new ArrayList<>();
        InputData inputData = new InputData(crime, peopleInvolved, place, 0);
        input1.add(inputData);
    }

    private static class InputData {
        @SerializedName("Crime")
        private int crime;
        @SerializedName("PeopleInvolved")
        private int peopleInvolved;
        @SerializedName("Place")
        private int place;
        @SerializedName("Numofdays")
        private int numofdays;

        public InputData(int crime, int peopleInvolved, int place, int numofdays) {
            this.crime = crime;
            this.peopleInvolved = peopleInvolved;
            this.place = place;
            this.numofdays = numofdays;
        }
    }
}