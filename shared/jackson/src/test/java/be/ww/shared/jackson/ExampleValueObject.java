package be.ww.shared.jackson;

import java.util.Objects;

class ExampleValueObject {
    private final String value;

    ExampleValueObject(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExampleValueObject that = (ExampleValueObject) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
