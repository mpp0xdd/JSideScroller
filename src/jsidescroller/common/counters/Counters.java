package jsidescroller.common.counters;

public final class Counters {

  private Counters() {
    // restrict instantiation
  }

  @SuppressWarnings("unchecked")
  public static <T extends Number> Counter<T> unmodifiableCounter(Counter<? extends T> counter) {
    if (counter.getClass() == UnmodifiableCounter.class) {
      return (Counter<T>) counter;
    }

    return new UnmodifiableCounter<>(counter);
  }

  private interface UnmodifiableCounterMixin<T extends Number> extends Counter<T> {

    @Override
    default void reset() {
      throw new UnsupportedOperationException();
    }

    @Override
    default void increment() {
      throw new UnsupportedOperationException();
    }

    @Override
    default T incrementAndGet() {
      throw new UnsupportedOperationException();
    }

    @Override
    default T getAndIncrement() {
      throw new UnsupportedOperationException();
    }

    @Override
    default void decrement() {
      throw new UnsupportedOperationException();
    }

    @Override
    default T decrementAndGet() {
      throw new UnsupportedOperationException();
    }

    @Override
    default T getAndDecrement() {
      throw new UnsupportedOperationException();
    }

    @Override
    default void add(T value) {
      throw new UnsupportedOperationException();
    }

    @Override
    default T addAndGet(T value) {
      throw new UnsupportedOperationException();
    }

    @Override
    default T getAndAdd(T value) {
      throw new UnsupportedOperationException();
    }

    @Override
    default Number wrapAround(T value) {
      throw new UnsupportedOperationException();
    }

    @Override
    default void incrementExact() throws CounterException {
      throw new UnsupportedOperationException();
    }

    @Override
    default T incrementExactAndGet() throws CounterException {
      throw new UnsupportedOperationException();
    }

    @Override
    default T getAndIncrementExact() throws CounterException {
      throw new UnsupportedOperationException();
    }

    @Override
    default void decrementExact() throws CounterException {
      throw new UnsupportedOperationException();
    }

    @Override
    default T decrementExactAndGet() throws CounterException {
      throw new UnsupportedOperationException();
    }

    @Override
    default T getAndDecrementExact() throws CounterException {
      throw new UnsupportedOperationException();
    }

    @Override
    default void addExact(T value) throws CounterException {
      throw new UnsupportedOperationException();
    }

    @Override
    default T addExactAndGet(T value) throws CounterException {
      throw new UnsupportedOperationException();
    }

    @Override
    default T getAndAddExact(T value) throws CounterException {
      throw new UnsupportedOperationException();
    }
  }

  private static class UnmodifiableCounter<T extends Number>
      implements Counter<T>, UnmodifiableCounterMixin<T> {

    private final Counter<? extends T> counter;

    public UnmodifiableCounter(Counter<? extends T> counter) {
      this.counter = counter;
    }

    @Override
    public T getCount() {
      return counter.getCount();
    }

    @Override
    public boolean isCounterStop() {
      return counter.isCounterStop();
    }

    @Override
    public T minimumValue() {
      return counter.minimumValue();
    }

    @Override
    public T maximumValue() {
      return counter.maximumValue();
    }

    @Override
    public T capacity() {
      return counter.capacity();
    }

    @Override
    public String toString() {
      return counter.toString();
    }
  }
}
