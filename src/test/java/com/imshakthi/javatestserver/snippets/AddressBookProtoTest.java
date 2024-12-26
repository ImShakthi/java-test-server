package com.imshakthi.javatestserver.snippets;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.imshakthi.javatestserver.AddressBookProtos;
import org.junit.jupiter.api.Test;

public class AddressBookProtoTest {

  @Test
  void testAddressBookProtos() {

    AddressBookProtos.Person person =
        AddressBookProtos.Person.newBuilder()
            .setId(1)
            .setName("Sakthi")
            .setEmail("test@mail.com")
            .build();

    AddressBookProtos.AddressBook addressBook =
        AddressBookProtos.AddressBook.newBuilder().addPeople(person).build();

    assertEquals(1, person.getId());
    assertEquals("Sakthi", person.getName());
    assertEquals("test@mail.com", person.getEmail());
    assertEquals(1, addressBook.getPeopleList().size());
  }
}
