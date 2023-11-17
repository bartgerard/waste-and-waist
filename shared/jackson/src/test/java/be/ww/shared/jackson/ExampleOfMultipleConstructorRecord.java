package be.ww.shared.jackson;

record ExampleOfMultipleConstructorRecord(
        String value1,
        String value2
) {
    ExampleOfMultipleConstructorRecord(final String value1) {
        this(value1, "value2");
    }
}
