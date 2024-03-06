package jsidescroller.common.counters;

public interface Counter<T extends Number> {

  T getCount();

  boolean isCounterStop();

  void reset();

  void increment();

  T incrementAndGet();

  T getAndIncrement();

  void decrement();

  T decrementAndGet();

  T getAndDecrement();

  void add(T value);

  T addAndGet(T value);

  T getAndAdd(T value);

  Number wrapAround(T value);

  void incrementExact() throws CounterException;

  T incrementExactAndGet() throws CounterException;

  T getAndIncrementExact() throws CounterException;

  void decrementExact() throws CounterException;

  T decrementExactAndGet() throws CounterException;

  T getAndDecrementExact() throws CounterException;

  void addExact(T value) throws CounterException;

  T addExactAndGet(T value) throws CounterException;

  T getAndAddExact(T value) throws CounterException;

  T minimumValue();

  T maximumValue();

  T capacity();

  @Override
  String toString();
}
