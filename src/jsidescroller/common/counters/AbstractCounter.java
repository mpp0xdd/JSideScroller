package jsidescroller.common.counters;

abstract class AbstractCounter<T extends Number, U extends Number> implements Counter<T> {

  protected U count;

  public AbstractCounter() {
    this.count = newInstance();
  }

  @Override
  public abstract T getCount();

  @Override
  public abstract boolean isCounterStop();

  @Override
  public void reset() {
    this.count = newInstance();
  }

  @Override
  public void increment() {
    getAndIncrement();
  }

  @Override
  public abstract T incrementAndGet();

  @Override
  public abstract T getAndIncrement();

  @Override
  public void decrement() {
    getAndDecrement();
  }

  @Override
  public abstract T decrementAndGet();

  @Override
  public abstract T getAndDecrement();

  @Override
  public void add(T value) {
    getAndAdd(value);
  }

  @Override
  public abstract T addAndGet(T value);

  @Override
  public abstract T getAndAdd(T value);

  @Override
  public abstract Number wrapAround(T value);

  @Override
  public void incrementExact() throws CounterException {
    getAndIncrementExact();
  }

  @Override
  public abstract T incrementExactAndGet() throws CounterException;

  @Override
  public abstract T getAndIncrementExact() throws CounterException;

  @Override
  public void decrementExact() throws CounterException {
    getAndDecrementExact();
  }

  @Override
  public abstract T decrementExactAndGet() throws CounterException;

  @Override
  public abstract T getAndDecrementExact() throws CounterException;

  @Override
  public void addExact(T value) throws CounterException {
    getAndAddExact(value);
  }

  @Override
  public abstract T addExactAndGet(T value) throws CounterException;

  @Override
  public abstract T getAndAddExact(T value) throws CounterException;

  @Override
  public abstract T minimumValue();

  @Override
  public abstract T maximumValue();

  @Override
  public abstract T capacity();

  @Override
  public String toString() {
    return count.toString();
  }

  protected CounterException newCounterException(T operand) {
    return new CounterException(this, operand);
  }

  protected abstract U newInstance();
}
