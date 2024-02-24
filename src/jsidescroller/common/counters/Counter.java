package jsidescroller.common.counters;

abstract class Counter<T extends Number> {

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

  public T getCount() {
    return count;
  }

  public void increment() {
    getAndIncrement();
  }

  public void decrement() {
    getAndDecrement();
  }

  public void add(T value) {
    getAndAdd(value);
  }

  public abstract T incrementAndGet();

  public abstract T getAndIncrement();

  public abstract T decrementAndGet();

  public abstract T getAndDecrement();

  public abstract T addAndGet(T value);

  public abstract T getAndAdd(T value);

  protected abstract T defaultValue();

  protected abstract T minimumValue();

  protected abstract T maximumValue();
}
