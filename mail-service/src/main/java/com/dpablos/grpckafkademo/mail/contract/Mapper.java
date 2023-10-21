package com.dpablos.grpckafkademo.mail.contract;

public interface Mapper<T, V> {

	V map(T input);

}
