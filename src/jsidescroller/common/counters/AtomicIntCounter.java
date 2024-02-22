package jsidescroller.common.counters;

import java.util.concurrent.atomic.AtomicInteger;

abstract class AtomicIntCounter extends Counter<AtomicInteger> {

  @Override
  public AtomicInteger incrementAndGet() {
    return wrap(count.updateAndGet(this::increment));
  }

  @Override
  public AtomicInteger getAndIncrement() {
    return wrap(count.getAndUpdate(this::increment));
  }

  @Override
  public AtomicInteger decrementAndGet() {
    return wrap(count.updateAndGet(this::decrement));
  }

  @Override
  public AtomicInteger getAndDecrement() {
    return wrap(count.getAndUpdate(this::decrement));
  }

  @Override
  public AtomicInteger addAndGet(AtomicInteger value) {
    return wrap(count.accumulateAndGet(value.get(), this::add));
  }

  @Override
  public AtomicInteger getAndAdd(AtomicInteger value) {
    return wrap(count.getAndAccumulate(value.get(), this::add));
  }

  @Override
  protected AtomicInteger defaultValue() {
    return minimumValue();
  }

  private int increment(final int count) {
    final int incrementedCount = count + 1;
    return incrementedCount <= maximumValue().getPlain() ? incrementedCount : count;
  }

  private int decrement(final int count) {
    final int decrementedCount = count - 1;
    return decrementedCount >= minimumValue().getPlain() ? decrementedCount : count;
  }

  private int add(final int count, final int value) {
    int newCount = count + value;
    if (newCount < minimumValue().getPlain()) {
      newCount = minimumValue().getPlain();
    } else if (newCount > maximumValue().getPlain()) {
      newCount = maximumValue().getPlain();
    }
    return newCount;
  }

  private AtomicInteger wrap(int value) {
    return new AtomicInteger(value);
  }
}
