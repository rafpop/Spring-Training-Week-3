package pl.mukova.cars.model;

public enum Color {

    NAVY_BLUE("Navy Blue"),
    MARINE("Marine"),
    BLACK("Black"),
    WHITE("White"),
    RED("Red"),
    BLUE("Blue"),
    GREY("Grey");

    private final String color;

    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
