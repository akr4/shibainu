package net.physalis.shibainu.presentation.requestid;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Radix32Test {

    @Test
    public void test1() {
        assertThat(Radix32.encode(0), is("0"));
        assertThat(Radix32.encode(1), is("1"));
        assertThat(Radix32.encode(31), is("Z"));
        assertThat(Radix32.encode(32), is("10"));
        assertThat(Radix32.encode(32 * 32 - 1), is("ZZ"));
    }

    @Test
    public void test2() {
        assertThat(Radix32.encode(0, 2), is("00"));
        assertThat(Radix32.encode(1, 2), is("01"));
        assertThat(Radix32.encode(31, 2), is("0Z"));
        assertThat(Radix32.encode(32, 2), is("10"));
        assertThat(Radix32.encode(32 * 32 - 1, 2), is("ZZ"));
    }
}
