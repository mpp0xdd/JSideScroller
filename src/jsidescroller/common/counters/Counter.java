package jsidescroller.common.counters;

abstract class Counter<T extends Number, U extends Number> {

  protected T count;

  public Counter() {
    this.count = defaultValue();
  }

  @Override
  public String toString() {
    return count.toString();
  }

  public void reset() {
    this.count = defaultValue();
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

  public abstract U getCount();

  public abstract U incrementAndGet();

  public abstract U getAndIncrement();

  public abstract U decrementAndGet();

  public abstract U getAndDecrement();

  public abstract U addAndGet(U value);

  public abstract U getAndAdd(U value);

  protected abstract T defaultValue();

  protected abstract T minimumValue();

  protected abstract T maximumValue();
}
