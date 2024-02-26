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

  public void decrement() {
    getAndDecrement();
  }

  public void add(U value) {
    getAndAdd(value);
  }

  public void addExact(U value) {
    getAndAddExact(value);
  }

  public abstract U getCount();

  public abstract U incrementAndGet();

  public abstract U getAndIncrement();

  public abstract U decrementAndGet();

  public abstract U getAndDecrement();

  public abstract U addAndGet(U value);

  public abstract U getAndAdd(U value);

  public abstract U addExactAndGet(U value);

  public abstract U getAndAddExact(U value);

  public abstract boolean isCounterStop();

  protected abstract T newInstance();

  protected abstract U minimumValue();

  protected abstract U maximumValue();
}
