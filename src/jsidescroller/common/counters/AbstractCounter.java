package jsidescroller.common.counters;

abstract class AbstractCounter<T extends Number, U extends Number> implements Counter<U> {

  protected T count;

  public AbstractCounter() {
    this.count = newInstance();
  }

  @Override
  public abstract U getCount();

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
  public abstract U incrementAndGet();

  @Override
  public abstract U getAndIncrement();

  @Override
  public void decrement() {
    getAndDecrement();
  }

  @Override
  public abstract U decrementAndGet();

  @Override
  public abstract U getAndDecrement();

  @Override
  public void add(U value) {
    getAndAdd(value);
  }

  @Override
  public abstract U addAndGet(U value);

  @Override
  public abstract U getAndAdd(U value);

  @Override
  public abstract Number wrapAround(U value);

  @Override
  public void incrementExact() throws CounterException {
    getAndIncrementExact();
  }

  @Override
  public abstract U incrementExactAndGet() throws CounterException;

  @Override
  public abstract U getAndIncrementExact() throws CounterException;

  @Override
  public void decrementExact() throws CounterException {
    getAndDecrementExact();
  }

  @Override
  public abstract U decrementExactAndGet() throws CounterException;

  @Override
  public abstract U getAndDecrementExact() throws CounterException;

  @Override
  public void addExact(U value) throws CounterException {
    getAndAddExact(value);
  }

  @Override
  public abstract U addExactAndGet(U value) throws CounterException;

  @Override
  public abstract U getAndAddExact(U value) throws CounterException;

  @Override
  public abstract U minimumValue();

  @Override
  public abstract U maximumValue();

  @Override
  public abstract U capacity();

  @Override
  public String toString() {
    return count.toString();
  }

  protected CounterException newCounterException(U operand) {
    return new CounterException(this, operand);
  }

  protected abstract T newInstance();
}
