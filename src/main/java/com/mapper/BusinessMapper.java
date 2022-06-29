package com.mapper;

import com.dto.*;
import com.model.*;

import java.util.stream.Collectors;

public class BusinessMapper {

    public static User getUser(UserDto userDto) {
        return new User(userDto.getId(), userDto.getPhone(), userDto.getPassword(), userDto.isActive(), userDto.getRole());
    }
    public static UserDto getUserDto(User user) {
        return new UserDto(user.getId(), user.getPhone(), user.getPassword(), user.isActive(), user.getRole());
    }
    public static User conversationRegisterUserDto(CustomerCreateRequestDto createRequestDto) {
        return new User(createRequestDto.getPhone(), createRequestDto.getPassword());
    }
    public static Customer conversationRegisterDto(CustomerCreateRequestDto createRequestDto) {
        return new Customer(createRequestDto.getName(), createRequestDto.getSurname(), createRequestDto.getPhone(), createRequestDto.getEmail());
    }
    public static Wallet walletConversation(WalletDto walletDto) {
        return new Wallet(walletDto.getId(),walletDto.getNumber(), walletDto.getBalance(),walletDto.getCustomerId());
    }
    public static Wallet getWallet(WalletDto walletDto) {
        return new Wallet(walletDto.getId(),walletDto.getNumber(), walletDto.getBalance(), walletDto.getCustomerId());
    }public static WalletDto getWalletDto(Wallet wallet) {
        return new WalletDto(wallet.getId(),wallet.getNumber(), wallet.getBalance(), wallet.getCustomerId());
    }
    public static Subscription convertSubscription(SubscriptionDto subscriptionDto) {
        return new Subscription(subscriptionDto.getId(),subscriptionDto.getName(), subscriptionDto.getDays(), subscriptionDto.isActive());
    }
    public static SubscriptionDto convertSubDto(Subscription subscription) {
        return new SubscriptionDto(subscription.getId(), subscription.getName(), subscription.getDays_amount(), subscription.isActive(),subscription.getCreated(),subscription.getUpdated());
    }
    public static SubscriptionDto getSubscriptionDto(Subscription subscription) {
        return new SubscriptionDto(subscription.getId(), subscription.getName(),
                subscription.getDays_amount(),
                subscription.isActive(),
                subscription.getCreated(),
                subscription.getUpdated(),
                subscription.getTariffs().stream().map(t -> getTariffDto(t)).collect(Collectors.toList())
        );
    }
    public static Tariff getTariff(TariffDto tariffDto) {
        return new Tariff(tariffDto.getId(),tariffDto.getName(), tariffDto.getType(), tariffDto.getPricePerDay(), tariffDto.isActive(), tariffDto.getCreated(),tariffDto.getUpdated());
    }
    public static Tariff convertTariff(TariffDto tariffDto) {
        return new Tariff(tariffDto.getName(), tariffDto.getType(), tariffDto.getPricePerDay());
    }
    public static TariffDto getTariffDto(Tariff tariff) {
        return new TariffDto(tariff.getId(),tariff.getName(), tariff.getType(), tariff.getPricePerDay(), tariff.isActive(), tariff.getCreated(), tariff.getUpdated());
    }
    public static Customer getCustomer(CustomerDto customerDto) {
        return new Customer(customerDto.getId(), customerDto.getName(), customerDto.getSurname(), customerDto.getPhone(), customerDto.getEmail(),
              customerDto.isActive(),customerDto.getCreated(),customerDto.getUpdated(),customerDto.getBalance());
    }
    public static CustomerDto getCustomerDto(Customer customer) {
       return  new CustomerDto(customer.getId(), customer.getName(), customer.getSurname(), customer.getPhone(), customer.getEmail(),
               customer.getServices().stream().map(subscription -> getSubscriptionDto(subscription)).collect(Collectors.toList()),
               customer.isActive(), customer.getCreated(),customer.getUpdated(), customer.getBalance());
    }
}
