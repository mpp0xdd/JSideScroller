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

  private static class UnmodifiableCounter<T extends Number> implements Counter<T> {

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
    public void reset() {
      throw new UnsupportedOperationException();
    }

    @Override
    public void increment() {
      throw new UnsupportedOperationException();
    }

    @Override
    public T incrementAndGet() {
      throw new UnsupportedOperationException();
    }

    @Override
    public T getAndIncrement() {
      throw new UnsupportedOperationException();
    }

    @Override
    public void decrement() {
      throw new UnsupportedOperationException();
    }

    @Override
    public T decrementAndGet() {
      throw new UnsupportedOperationException();
    }

    @Override
    public T getAndDecrement() {
      throw new UnsupportedOperationException();
    }

    @Override
    public void add(T value) {
      throw new UnsupportedOperationException();
    }

    @Override
    public T addAndGet(T value) {
      throw new UnsupportedOperationException();
    }

    @Override
    public T getAndAdd(T value) {
      throw new UnsupportedOperationException();
    }

    @Override
    public Number wrapAround(T value) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void incrementExact() throws CounterException {
      throw new UnsupportedOperationException();
    }

    @Override
    public T incrementExactAndGet() throws CounterException {
      throw new UnsupportedOperationException();
    }

    @Override
    public T getAndIncrementExact() throws CounterException {
      throw new UnsupportedOperationException();
    }

    @Override
    public void decrementExact() throws CounterException {
      throw new UnsupportedOperationException();
    }

    @Override
    public T decrementExactAndGet() throws CounterException {
      throw new UnsupportedOperationException();
    }

    @Override
    public T getAndDecrementExact() throws CounterException {
      throw new UnsupportedOperationException();
    }

    @Override
    public void addExact(T value) throws CounterException {
      throw new UnsupportedOperationException();
    }

    @Override
    public T addExactAndGet(T value) throws CounterException {
      throw new UnsupportedOperationException();
    }

    @Override
    public T getAndAddExact(T value) throws CounterException {
      throw new UnsupportedOperationException();
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
