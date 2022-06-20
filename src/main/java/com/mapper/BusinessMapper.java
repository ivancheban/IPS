package com.mapper;

import com.dto.*;
import com.model.*;

import java.util.stream.Collectors;

public class BusinessMapper {

    public static User getUser(UserDto userDto) {
        return new User(userDto.getPhone(), userDto.getPassword(), userDto.isActive(), userDto.getRole(), userDto.getCreated(), userDto.getUpdated());
    }
    public static UserDto getUserDto(User user) {
        return new UserDto(user.getPhone(), user.getPassword(), user.isActive(), user.getRole(), user.getCreated(), user.getUpdated());
    }

    public static User conversationRegisterUserDto(CustomerCreateRequestDto createRequestDto) {
        return new User(createRequestDto.getPhone(), createRequestDto.getPassword());
    }

    public static Customer conversationRegisterDto(CustomerCreateRequestDto createRequestDto) {
        return new Customer(createRequestDto.getName(), createRequestDto.getSurname(), createRequestDto.getPhone(), createRequestDto.getEmail());
    }

    public static Wallet walletConversation(WalletDto walletDto) {
        return new Wallet(walletDto.getNumber(), walletDto.getBalance());
    }

    public static Wallet getWallet(WalletDto walletDto) {
        return new Wallet(walletDto.getNumber(), walletDto.getBalance());
    }

    public static WalletDto getWalletDto(Wallet wallet) {
        return new WalletDto(wallet.getNumber(), wallet.getBalance());
    }

    public static Subscription convertSubscription(SubscriptionDto subscriptionDto) {
        return new Subscription(subscriptionDto.getName(), subscriptionDto.getDays(), subscriptionDto.isActive());
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
        return new Tariff(tariffDto.getName(), tariffDto.getType(), tariffDto.getPricePerDay());
    }

    public static Tariff convertTariff(TariffDto tariffDto) {
        return new Tariff(tariffDto.getName(), tariffDto.getType(), tariffDto.getPricePerDay());
    }

    public static TariffDto getTariffDto(Tariff tariff) {
        return new TariffDto(tariff.getName(), tariff.getType(), tariff.getPricePerDay(), tariff.isActive(), tariff.getCreated(), tariff.getUpdated());
    }

    public Limit getLimit(LimitDto limitDto) {
        return new Limit(limitDto.getName(), limitDto.getAmount(), limitDto.isActive(), limitDto.getCreated(), limitDto.getUpdated());
    }

    public LimitDto getLimitDto(Limit limit) {
        return new LimitDto(limit.getName(), limit.getAmount(), limit.isActive(), limit.getCreated(), limit.getUpdated());
    }

    public static Customer getCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();

        customer.setName(customerDto.getName());
        customer.setSurname(customerDto.getSurname());
        customer.setPhone(customerDto.getPhone());
        customer.setEmail(customerDto.getEmail());
//        customer.setServices(collectionToList(customerDto.getServicesDto(), SubscriptionToEntity));
//        customer.setWallet(getWallet(customerDto.getWalletDto()));
        customer.setActive(customerDto.isActive());
        customer.setCreated(customerDto.getCreated());
        customer.setUpdated(customerDto.getUpdated());

        return customer;
    }

    public static CustomerDto getCustomerDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();

        customerDto.setName(customer.getName());
        customerDto.setSurname(customerDto.getSurname());
        customerDto.setPhone(customer.getPhone());
        customerDto.setEmail(customerDto.getEmail());
//        customerDto.setServicesDto(collectionToList(customer.getServices(), SubscriptionToDto));
        // customerDto.setWalletDto(getWalletDto(customer.getWallet()));
        customerDto.setActive(customer.isActive());
        customerDto.setCreated(customer.getCreated());
        customerDto.setUpdated(customer.getUpdated());

        return customerDto;
    }
}
