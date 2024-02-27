package jsidescroller.common.counters;

abstract class Counter<T extends Number, U extends Number> {

  protected T count;

  public Counter() {
    this.count = newInstance();
  }

  @Override
  public String toString() {
    return count.toString();
  }

  public void reset() {
    this.count = newInstance();
  }

  public void increment() {
    getAndIncrement();
  }

  public void incrementExact() throws CounterException {
    getAndIncrementExact();
  }

  public void decrement() {
    getAndDecrement();
  }

  public void decrementExact() throws CounterException {
    getAndDecrementExact();
  }

  public void add(U value) {
    getAndAdd(value);
  }

  public void addExact(U value) throws CounterException {
    getAndAddExact(value);
  }

  public abstract U getCount();

  public abstract U incrementAndGet();

  public abstract U getAndIncrement();

  public abstract U incrementExactAndGet() throws CounterException;

  public abstract U getAndIncrementExact() throws CounterException;

  public abstract U decrementAndGet();

  public abstract U getAndDecrement();

  public abstract U decrementExactAndGet() throws CounterException;

  public abstract U getAndDecrementExact() throws CounterException;

  public abstract U addAndGet(U value);

  public abstract U getAndAdd(U value);

  public abstract U addExactAndGet(U value) throws CounterException;

  public abstract U getAndAddExact(U value) throws CounterException;

  // TODO: wrapAround

  public abstract boolean isCounterStop();

  protected abstract T newInstance();

  protected abstract U minimumValue();

  protected abstract U maximumValue();

  protected abstract U capacity();

  protected abstract CounterException newCounterException(U operand);
}
