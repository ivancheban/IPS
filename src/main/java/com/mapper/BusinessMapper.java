package com.mapper;

import com.dto.*;
import com.model.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BusinessMapper {


    public <A, R> Set<R> collectionToSet(Collection<A> collection, Function<A, R> mapper) {
        return collection.stream().map(mapper).collect(Collectors.toSet());
    }

    public <A, R> List<R> collectionToList(Collection<A> collection, Function<A, R> mapper) {
        return collection.stream().map(mapper).collect(Collectors.toList());
    }

    public Function<Customer, CustomerDto> CustomerToDto = this::getCustomerDto;
    public Function<CustomerDto, Customer> CustomerToEntity = this::getCustomer;

    public Function<Wallet, WalletDto> WalletToDto = this::getWalletDto;
    public Function<WalletDto, Wallet> WalletToEntity = this::getWallet;

    public Function<Subscription, SubscriptionDto> SubscriptionToDto = this::getSubscriptionDto;
    public Function<SubscriptionDto, Subscription> SubscriptionToEntity = this::getSubscription;

    public Function<Tariff, TariffDto> TariffToDto = this::getTariffDto;
    public Function<TariffDto, Tariff> TariffToEntity = this::getTariff;

    public Function<Limit, LimitDto> LimitToDto = this::getLimitDto;
    public Function<LimitDto, Limit> LimitToEntity = this::getLimit;


    public User getUser(UserDto userDto) {
        User user = new User();

        user.setPhone(userDto.getPhone());
        user.setPassword(userDto.getPassword());
        user.setActive(userDto.isActive());
        user.setRole(userDto.getRole());
        user.setCreated(userDto.getCreated());
        user.setUpdated(userDto.getUpdated());

        return user;
    }

    public UserDto getUserDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setPhone(user.getPhone());
        userDto.setPassword(user.getPassword());
        userDto.setActive(userDto.isActive());
        userDto.setRole(userDto.getRole());
        userDto.setCreated(userDto.getCreated());
        userDto.setUpdated(user.getUpdated());

        return userDto;
    }

    public static User conversationRegisterUserDto(CustomerCreateRequestDto createRequestDto) {
        return new User(createRequestDto.getPhone(), createRequestDto.getPassword());

//            user.setPhone(createRequestDto.getPhone());
//            user.setPassword(createRequestDto.getPassword());
//            user.setActive(false);
//            user.setRole(Role.CLIENT);
//            user.setCreated(LocalDateTime.now());
//            user.setUpdated(LocalDateTime.now());



    }
    public static Customer conversationRegisterDto(CustomerCreateRequestDto createRequestDto) {
        Customer customer = new Customer(createRequestDto.getName(),createRequestDto.getSurname(),createRequestDto.getPhone(),createRequestDto.getEmail());
//        customer.setName(createRequestDto.getName());
//        customer.setSurname(createRequestDto.getSurname());
//        customer.setPhone(createRequestDto.getPhone());
//        customer.setEmail(createRequestDto.getEmail());
//        customer.setServices(new ArrayList<>());
////        customer.setWallet(new Wallet());
//        customer.setActive(customer.isActive());
//        customer.setCreated(LocalDateTime.now());
//        customer.setUpdated(LocalDateTime.now());
        return customer;
    }


public  static Wallet walletConversation(WalletDto walletDto){
        return new Wallet(walletDto.getNumber(),walletDto.getBalance());
}

    public Wallet getWallet(WalletDto walletDto) {
        Wallet wallet = new Wallet(walletDto.getNumber(), walletDto.getBalance());

        wallet.setNumber(walletDto.getNumber());
        wallet.setBalance(walletDto.getBalance());


        return wallet;
    }

    public WalletDto getWalletDto(Wallet wallet) {
        WalletDto walletDto = new WalletDto();

        walletDto.setNumber(wallet.getNumber());
        walletDto.setBalance(walletDto.getBalance());


        return walletDto;
    }


    public Customer getCustomer(CustomerDto customerDto) {
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

    public CustomerDto getCustomerDto(Customer customer) {
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

    public Subscription getSubscription(SubscriptionDto subscriptionDto) {
    Subscription subscription = new Subscription();

        subscription.setName(subscriptionDto.getName());
        subscription.setDays_amount(subscriptionDto.getDays());
        subscription.setActive(subscriptionDto.isActive());
        subscription.setCreated(subscriptionDto.getCreated());
        subscription.setUpdated(subscriptionDto.getUpdated());

        return subscription;

    }
    public static Subscription convertSubscription(SubscriptionDto subscriptionDto) {
        return new Subscription(subscriptionDto.getName(), subscriptionDto.getDays());
    }

        public SubscriptionDto getSubscriptionDto(Subscription subscription) {
        SubscriptionDto subscriptionDto = new SubscriptionDto();

        subscriptionDto.setName(subscription.getName());
        subscriptionDto.setDays(subscription.getDays_amount());
        subscriptionDto.setActive(subscription.isActive());
        subscriptionDto.setCreated(subscriptionDto.getCreated());
        subscriptionDto.setUpdated(subscriptionDto.getUpdated());

        return subscriptionDto;
    }

    public Tariff getTariff(TariffDto tariffDto) {
        return new Tariff(tariffDto.getName(),tariffDto.getType(),tariffDto.getPricePerDay());




    }
    public static Tariff convertTariff(TariffDto tariffDto) {
        return new Tariff(tariffDto.getName(), tariffDto.getType(), tariffDto.getPricePerDay());
    }

    public TariffDto getTariffDto(Tariff tariff) {
        TariffDto tariffDto = new TariffDto();

        tariffDto.setName(tariff.getName());
        tariffDto.setType(tariff.getType());
        tariffDto.setPricePerDay(tariff.getPricePerDay());
        tariffDto.setActive(tariff.isActive());
        tariffDto.setCreated(tariff.getCreated());
        tariffDto.setUpdated(tariff.getUpdated());

        return tariffDto;
    }

    public Limit getLimit(LimitDto limitDto) {
        Limit limit = new Limit();

        limit.setName(limitDto.getName());
        limit.setAmount(limitDto.getAmount());
        limit.setActive(limitDto.isActive());
        limit.setCreated(limitDto.getCreated());
        limit.setUpdated(limitDto.getUpdated());

        return limit;
    }

    public LimitDto getLimitDto(Limit limit) {
        LimitDto limitDto = new LimitDto();

        limitDto.setName(limit.getName());
        limitDto.setAmount(limit.getAmount());
        limitDto.setActive(limit.isActive());
        limitDto.setCreated(limit.getCreated());
        limitDto.setUpdated(limit.getUpdated());

        return limitDto;
    }

}
