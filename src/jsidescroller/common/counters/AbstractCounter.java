package jsidescroller.common.counters;

abstract class AbstractCounter<T extends Number, U extends Number> {

  protected T count;

  public AbstractCounter() {
    this.count = newInstance();
  }

  public abstract U getCount();

  public abstract boolean isCounterStop();

  public void reset() {
    this.count = newInstance();
  }

  public void increment() {
    getAndIncrement();
  }

  public abstract U incrementAndGet();

  public abstract U getAndIncrement();

  public void decrement() {
    getAndDecrement();
  }

  public abstract U decrementAndGet();

  public abstract U getAndDecrement();

  public void add(U value) {
    getAndAdd(value);
  }

  public abstract U addAndGet(U value);

  public abstract U getAndAdd(U value);

  public abstract Number wrapAround(U value);

  public void incrementExact() throws CounterException {
    getAndIncrementExact();
  }

  public abstract U incrementExactAndGet() throws CounterException;

  public abstract U getAndIncrementExact() throws CounterException;

  public void decrementExact() throws CounterException {
    getAndDecrementExact();
  }

  public abstract U decrementExactAndGet() throws CounterException;

  public abstract U getAndDecrementExact() throws CounterException;

  public void addExact(U value) throws CounterException {
    getAndAddExact(value);
  }

  public abstract U addExactAndGet(U value) throws CounterException;

  public abstract U getAndAddExact(U value) throws CounterException;

  public abstract U minimumValue();

  public abstract U maximumValue();

  public abstract U capacity();

  @Override
  public String toString() {
    return count.toString();
  }

  protected abstract T newInstance();

  protected abstract CounterException newCounterException(U operand);
}
