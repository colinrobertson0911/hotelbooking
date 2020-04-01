insert into hotelowner(hotelOwnerId, username, password, email, name) values (HOTELOWNER_SEQ.nextval,'user1','password','user1@email.com','user one');
insert into hotelowner(hotelOwnerId, username, password, email, name) values (HOTELOWNER_SEQ.nextval,'user2','password','user2@email.com','Wee Pete');
insert into hotelowner(hotelOwnerId, username, password, email, name) values (HOTELOWNER_SEQ.nextval,'user3','password','user3@email.com','Wee Dan');


insert into users(userId, username, password, email, userType) values (USER_SEQ.nextval, 'admin1', 'password', 'admin@email.com', 'ADMIN');


insert into hotel(hotelId, hotelName, numOfRooms, address, postcode, city, ammenities, starRating, verified) values (HOTEL_SEQ.nextval, 'Travelodge Glasgow', 53,'1 main street', 'g43 6pq', 'Glasgow','none', 3, true);
insert into hotel(hotelId, hotelName, numOfRooms, address, postcode, city, ammenities, starRating, verified) values (HOTEL_SEQ.nextval, 'Yotel', 50,'some street','EH71 7FA', 'Edinburgh','bowling alley', 4, true);
insert into hotel(hotelId, hotelName, numOfRooms, address, postcode, city, ammenities, starRating, verified) values (HOTEL_SEQ.nextval, 'Radisson Blue', 43,'123 argyle street','G3 6OP', 'Glasgow','Conference Rooms, Bars, Near Central Station', 4, true);
insert into hotel(hotelId, hotelName, numOfRooms, address, postcode, city, ammenities, starRating, verified) values (HOTEL_SEQ.nextval, 'Mar Hall', 112, 'Backwoods Erskine', 'G72 3RP', 'Glasgow','Breakfast', 5, true);
insert into hotel(hotelId, hotelName, numOfRooms, address, postcode, city, ammenities, starRating, verified) values (HOTEL_SEQ.nextval, 'Travelodge Aberdeen', 90, '15 Princess Street','AB12 4PQ', 'Aberdeen','Free Stab vests', 3, true );
insert into hotel(hotelId, hotelName, numOfRooms, address, postcode, city, ammenities, starRating, verified) values (HOTEL_SEQ.nextval, 'Friars Wynd', 10,'12 Frairs Street', 'FK8 1HA', 'Stirling','Restaurant', 2, true);
insert into hotel(hotelId, hotelName, numOfRooms, address, postcode, city, ammenities, starRating, verified) values (HOTEL_SEQ.nextval, 'Balmoral', 5, '13 Princess Street','EH13 5YT', 'Edinburgh','car park, spa, restaurant, bar, laundry, pool and gym', 5, true);
insert into hotel(hotelId, hotelName, numOfRooms, address, postcode, city, ammenities, starRating, verified) values (HOTEL_SEQ.nextval, 'Beehive BnB', 3,'Other street','D31 4PQ', 'Dundee','A Map out of dundee', 1, true);
insert into hotel(hotelId, hotelName, numOfRooms, address, postcode, city, ammenities, starRating, verified) values (HOTEL_SEQ.nextval, 'Sherbrook Hotel', 15, '31 Nithsdale Road','G41 7PQ', 'Glasgow','Wedding Suite, Bar, Restaurant', 4, true);
insert into hotel(hotelId, hotelName, numOfRooms, address, postcode, city, ammenities, starRating, verified) values (HOTEL_SEQ.nextval, 'Stirling Highland hotel', 25,'12 King Street', 'FK8 2RE', 'Stirling','Spa, Carpark, Restaurant', 4, true);

insert into hotelOwner_hotel(hotelOwner_hotelOwnerId, hotel_hotelId) values (1,1);
insert into hotelOwner_hotel(hotelOwner_hotelOwnerId, hotel_hotelId) values (1,2);
insert into hotelOwner_hotel(hotelOwner_hotelOwnerId, hotel_hotelId) values (2,3);
insert into hotelOwner_hotel(hotelOwner_hotelOwnerId, hotel_hotelId) values (3,4);
insert into hotelOwner_hotel(hotelOwner_hotelOwnerId, hotel_hotelId) values (3,5);



insert into room(roomId, roomType, price) values (ROOM_SEQ.nextval, 'STANDARD', '60.00');
insert into room(roomId, roomType, price) values (ROOM_SEQ.nextval, 'LUXURY', '80.00');
insert into room(roomId, roomType, price) values (ROOM_SEQ.nextval, 'DELUXE', '100.00');
insert into room(roomId, roomType, price) values (ROOM_SEQ.nextval, 'SUITE', '120.00');

insert into hotel_room(hotel_hotelId, room_roomId) values (1,1);
insert into hotel_room(hotel_hotelId, room_roomId) values (1,4);

insert into hotel_room(hotel_hotelId, room_roomId) values (2,2);
insert into hotel_room(hotel_hotelId, room_roomId) values (2,3);
