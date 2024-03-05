package jsidescroller.common.counters;

public final class Counters {

  private Counters() {
    // restrict instantiation
  }

  @SuppressWarnings("unchecked")
  public static <T extends Number, U extends Number> Counter<T, U> unmodifiableCounter(
      Counter<? extends T, ? extends U> counter) {
    if (counter.getClass() == UnmodifiableCounter.class) {
      return (Counter<T, U>) counter;
    }

    return new UnmodifiableCounter<>(counter);
  }

  private static class UnmodifiableCounter<T extends Number, U extends Number>
      extends Counter<T, U> {

    private final Counter<? extends T, ? extends U> counter;

    public UnmodifiableCounter(Counter<? extends T, ? extends U> counter) {
      this.counter = counter;
    }

    @Override
    public U getCount() {
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
    public U incrementAndGet() {
      throw new UnsupportedOperationException();
    }

    @Override
    public U getAndIncrement() {
      throw new UnsupportedOperationException();
    }

    @Override
    public void decrement() {
      throw new UnsupportedOperationException();
    }

    @Override
    public U decrementAndGet() {
      throw new UnsupportedOperationException();
    }

    @Override
    public U getAndDecrement() {
      throw new UnsupportedOperationException();
    }

    @Override
    public void add(U value) {
      throw new UnsupportedOperationException();
    }

    @Override
    public U addAndGet(U value) {
      throw new UnsupportedOperationException();
    }

    @Override
    public U getAndAdd(U value) {
      throw new UnsupportedOperationException();
    }

    @Override
    public Number wrapAround(U value) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void incrementExact() throws CounterException {
      throw new UnsupportedOperationException();
    }

    @Override
    public U incrementExactAndGet() throws CounterException {
      throw new UnsupportedOperationException();
    }

    @Override
    public U getAndIncrementExact() throws CounterException {
      throw new UnsupportedOperationException();
    }

    @Override
    public void decrementExact() throws CounterException {
      throw new UnsupportedOperationException();
    }

    @Override
    public U decrementExactAndGet() throws CounterException {
      throw new UnsupportedOperationException();
    }

    @Override
    public U getAndDecrementExact() throws CounterException {
      throw new UnsupportedOperationException();
    }

    @Override
    public void addExact(U value) throws CounterException {
      throw new UnsupportedOperationException();
    }

    @Override
    public U addExactAndGet(U value) throws CounterException {
      throw new UnsupportedOperationException();
    }

    @Override
    public U getAndAddExact(U value) throws CounterException {
      throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
      return counter.toString();
    }

    @Override
    protected T newInstance() {
      return null;
    }

    @Override
    public U minimumValue() {
      return counter.minimumValue();
    }

    @Override
    public U maximumValue() {
      return counter.maximumValue();
    }

    @Override
    public U capacity() {
      return counter.capacity();
    }

    @Override
    protected CounterException newCounterException(U operand) {
      return null;
    }
  }
}
