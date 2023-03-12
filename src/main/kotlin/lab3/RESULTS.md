# Part 1

## 1 
```
db.products.insertMany([
    {
        "category": "phone",
        "model": "iphone 10",
        "producer": "apple",
        "price": 600,
        "5G": true
    },
    {
        "category": "phone",
        "model": "google pixel",
        "producer": "google",
        "price": 500,
        "5G": true
    },
    {
        "category": "phone",
        "model": "samsung fold 4",
        "producer": "samsung",
        "price": 800,
        "5G": false,
        "foldable": true
    },
    {
        "category": "laptop",
        "model": "macbook pro m2",
        "producer": "apple",
        "price": 1600,
        "processor": "m2"
    },
    {
        "category": "laptop",
        "model": "think pad",
        "producer": "lenovo",
        "price": 1200,
        "processor": "i7"
    },
    {
        "category": "laptop",
        "model": "chromebook",
        "producer": "google",
        "price": 1000,
        "processor": "i5",
        "touchscreen": true
    },
    {
        "category": "watch",
        "model": "apple watch series 5",
        "producer": "apple",
        "price": 400,
        "gps": true    
    },
    {
        "category": "watch",
        "model": "samsung watch 2",
        "producer": "samsung",
        "price": 300,
        "gps": true    
    },
])
```
```
{ acknowledged: true,
  insertedIds: 
   { '0': ObjectId("640e04e6b95d231fbd679400"),
     '1': ObjectId("640e04e6b95d231fbd679401"),
     '2': ObjectId("640e04e6b95d231fbd679402"),
     '3': ObjectId("640e04e6b95d231fbd679403"),
     '4': ObjectId("640e04e6b95d231fbd679404"),
     '5': ObjectId("640e04e6b95d231fbd679405"),
     '6': ObjectId("640e04e6b95d231fbd679406"),
     '7': ObjectId("640e04e6b95d231fbd679407") } }
  ```

### 2 Напишіть запит, який виводіть усі товари (відображення у JSON)
```
db.products.find({})
```
```
{ _id: ObjectId("640e04e6b95d231fbd679400"),
  category: 'phone',
  model: 'iphone 10',
  producer: 'apple',
  price: 600,
  '5G': true }
{ _id: ObjectId("640e04e6b95d231fbd679401"),
  category: 'phone',
  model: 'google pixel',
  producer: 'google',
  price: 500,
  '5G': true }
{ _id: ObjectId("640e04e6b95d231fbd679402"),
  category: 'phone',
  model: 'samsung fold 4',
  producer: 'samsung',
  price: 800,
  '5G': false,
  foldable: true }
{ _id: ObjectId("640e04e6b95d231fbd679403"),
  category: 'laptop',
  model: 'macbook pro m2',
  producer: 'apple',
  price: 1600,
  processor: 'm2' }
{ _id: ObjectId("640e04e6b95d231fbd679404"),
  category: 'laptop',
  model: 'think pad',
  producer: 'lenovo',
  price: 1200,
  processor: 'i7' }
{ _id: ObjectId("640e04e6b95d231fbd679405"),
  category: 'laptop',
  model: 'chromebook',
  producer: 'google',
  price: 1000,
  processor: 'i5',
  touchscreen: true }
{ _id: ObjectId("640e04e6b95d231fbd679406"),
  category: 'watch',
  model: 'apple watch series 5',
  producer: 'apple',
  price: 400,
  gps: true }
{ _id: ObjectId("640e04e6b95d231fbd679407"),
  category: 'watch',
  model: 'samsung watch 2',
  producer: 'samsung',
  price: 300,
  gps: true }
```

### 3 Підрахуйте скільки товарів у певної категорії
```
db.products.countDocuments({ category: 'laptop' })
```
```
3
```

### 4 Підрахуйте скільки є різних категорій товарів
```
db.products.distinct('category').length
```
```
3
```

### 5 Виведіть список всіх виробників товарів без повторів
```
db.products.distinct('producer')
```
```
[ 'apple', 'google', 'lenovo', 'samsung' ]
```

### 6 Напишіть запити, які вибирають товари за різними критеріям і їх сукупності:
#### a) категорія та ціна (в проміжку) - конструкція $and
```
db.products.find({
    $and: [
        { category: 'laptop' },
        { price: { $gt: 1000, $lt: 1500 }}
    ]})
```
```
{ _id: ObjectId("640e04e6b95d231fbd679404"),
  category: 'laptop',
  model: 'think pad',
  producer: 'lenovo',
  price: 1200,
  processor: 'i7' }
```
#### b) модель чи одна чи інша - конструкція $or
```
db.products.find({
    $or: [
        { model: 'chromebook' },
        { model: 'think pad' }
    ]})
```
```
{ _id: ObjectId("640e04e6b95d231fbd679404"),
  category: 'laptop',
  model: 'think pad',
  producer: 'lenovo',
  price: 1200,
  processor: 'i7' }
{ _id: ObjectId("640e04e6b95d231fbd679405"),
  category: 'laptop',
  model: 'chromebook',
  producer: 'google',
  price: 1000,
  processor: 'i5',
  touchscreen: true }
```
#### c) виробники з переліку - конструкція $in
```
db.products.find({ producer: { $in: ['apple', 'samsung'] } })
```
```
{ _id: ObjectId("640e04e6b95d231fbd679400"),
  category: 'phone',
  model: 'iphone 10',
  producer: 'apple',
  price: 600,
  '5G': true }
{ _id: ObjectId("640e04e6b95d231fbd679402"),
  category: 'phone',
  model: 'samsung fold 4',
  producer: 'samsung',
  price: 800,
  '5G': false,
  foldable: true }
{ _id: ObjectId("640e04e6b95d231fbd679403"),
  category: 'laptop',
  model: 'macbook pro m2',
  producer: 'apple',
  price: 1600,
  processor: 'm2' }
{ _id: ObjectId("640e04e6b95d231fbd679406"),
  category: 'watch',
  model: 'apple watch series 5',
  producer: 'apple',
  price: 400,
  gps: true }
{ _id: ObjectId("640e04e6b95d231fbd679407"),
  category: 'watch',
  model: 'samsung watch 2',
  producer: 'samsung',
  price: 300,
  gps: true }
```

### 7 Оновить певні товари, змінивши існуючі значення і додайте нові властивості (характеристики) усім товарам за певним критерієм
```
db.products.updateMany({ producer: 'apple' }, { $set: { producer: 'pineapple', website: 'https://r.mtdv.me/pineapple' } })```
```
```
{ acknowledged: true,
insertedId: null,
matchedCount: 3,
modifiedCount: 3,
upsertedCount: 0 }
```

### 8 Знайдіть товари у яких є (присутнє поле) певні властивості
```
db.products.find({ gps: { $exists: true } })
```
```
{ _id: ObjectId("640e04e6b95d231fbd679406"),
  category: 'watch',
  model: 'apple watch series 5',
  producer: 'pineapple',
  price: 400,
  gps: true,
  website: 'https://r.mtdv.me/pineapple' }
{ _id: ObjectId("640e04e6b95d231fbd679407"),
  category: 'watch',
  model: 'samsung watch 2',
  producer: 'samsung',
  price: 300,
  gps: true }
```

### 9 Для знайдених товарів збільшіть їх вартість на певну суму
```
db.products.updateMany({ gps: { $exists: true } }, { $inc: { price: 100  } })
```
```
{ acknowledged: true,
  insertedId: null,
  matchedCount: 2,
  modifiedCount: 2,
  upsertedCount: 0 }
```

## 2
```
db.orders.insertMany([
	{    
		"order_number" : 1,
		"date" : ISODate("2023-03-10"),
		"total_sum" : 123.4,
		"customer" : {
				"name" : "Yaroslav",
				"surname" : "Borodaienko",
				"phones" : [ 9876543, 1234567],
				"address" : "02000, Akademika Yanhelya St, 18-20, Kyiv, Ukraine, 02000"
		},
		"payment" : {
				"card_owner" : "Yaroslav Borodaienko",
				"cardId" : 12345678
		},
		"items_id" : ["640e2d42b95d231fbd679408", "640e2d42b95d231fbd679409"]
	},
	{    
		"order_number" : 2,
		"date" : ISODate("2023-03-11"),
		"total_sum" : 425.45,
		"customer" : {
				"name" : "Yaroslav",
				"surname" : "Borodaienko",
				"phones" : [ 9876543, 1234567],
				"address" : "02000, Akademika Yanhelya St, 18-20, Kyiv, Ukraine, 02000"
		},
		"payment" : {
				"card_owner" : "Yaroslav Borodaienko",
				"cardId" : 12345678
		},
		"items_id" : ["640e2d42b95d231fbd679409", "640e2d42b95d231fbd67940c", "640e2d42b95d231fbd67940d"]
	},
	{    
		"order_number" : 3,
		"date" : ISODate("2023-03-13"),
		"total_sum" : 512.0,
		"customer" : {
				"name" : "Mykola",
				"surname" : "Parasyk",
				"phones" : [ 82734985 ],
				"address" : "Lviv"
		},
		"payment" : {
				"card_owner" : "Mykola Parasyk",
				"cardId" : 87654321
		},
		"items_id" : ["640e2d42b95d231fbd67940e", "640e2d42b95d231fbd67940f"]
	}
])
```
```
{ acknowledged: true,
  insertedIds: 
   { '0': ObjectId("640e2f3db95d231fbd679413"),
     '1': ObjectId("640e2f3db95d231fbd679414"),
     '2': ObjectId("640e2f3db95d231fbd679415") } }
```

### 2 Виведіть всі замовлення
```
db.orders.find({})
```
```
{ _id: ObjectId("640e2f3db95d231fbd679413"),
  order_number: 1,
  date: 2023-03-10T00:00:00.000Z,
  total_sum: 123.4,
  customer: 
   { name: 'Yaroslav',
     surname: 'Borodaienko',
     phones: [ 9876543, 1234567 ],
     address: '02000, Akademika Yanhelya St, 18-20, Kyiv, Ukraine, 02000' },
  payment: { card_owner: 'Yaroslav Borodaienko', cardId: 12345678 },
  items_id: [ '640e2d42b95d231fbd679408', '640e2d42b95d231fbd679409' ] }
{ _id: ObjectId("640e2f3db95d231fbd679414"),
  order_number: 2,
  date: 2023-03-11T00:00:00.000Z,
  total_sum: 425.45,
  customer: 
   { name: 'Yaroslav',
     surname: 'Borodaienko',
     phones: [ 9876543, 1234567 ],
     address: '02000, Akademika Yanhelya St, 18-20, Kyiv, Ukraine, 02000' },
  payment: { card_owner: 'Yaroslav Borodaienko', cardId: 12345678 },
  items_id: 
   [ '640e2d42b95d231fbd679409',
     '640e2d42b95d231fbd67940c',
     '640e2d42b95d231fbd67940d' ] }
{ _id: ObjectId("640e2f3db95d231fbd679415"),
  order_number: 3,
  date: 2023-03-13T00:00:00.000Z,
  total_sum: 512,
  customer: 
   { name: 'Mykola',
     surname: 'Parasyk',
     phones: [ 82734985 ],
     address: 'Lviv' },
  payment: { card_owner: 'Mykola Parasyk', cardId: 87654321 },
  items_id: [ '640e2d42b95d231fbd67940e', '640e2d42b95d231fbd67940f' ] }
```

### 3 Виведіть замовлення з вартістю більше певного значення
```
db.orders.find({ total_sum: { $gt: 500.0 }})
```
```
{ _id: ObjectId("640e2f3db95d231fbd679415"),
  order_number: 3,
  date: 2023-03-13T00:00:00.000Z,
  total_sum: 512,
  customer: 
   { name: 'Mykola',
     surname: 'Parasyk',
     phones: [ 82734985 ],
     address: 'Lviv' },
  payment: { card_owner: 'Mykola Parasyk', cardId: 87654321 },
  items_id: [ '640e2d42b95d231fbd67940e', '640e2d42b95d231fbd67940f' ] }
```

### 4 Знайдіть замовлення зроблені одним замовником
```
db.orders.find({ "customer.name": 'Yaroslav' })
```
```
{ _id: ObjectId("640e2f3db95d231fbd679413"),
  order_number: 1,
  date: 2023-03-10T00:00:00.000Z,
  total_sum: 123.4,
  customer: 
   { name: 'Yaroslav',
     surname: 'Borodaienko',
     phones: [ 9876543, 1234567 ],
     address: '02000, Akademika Yanhelya St, 18-20, Kyiv, Ukraine, 02000' },
  payment: { card_owner: 'Yaroslav Borodaienko', cardId: 12345678 },
  items_id: [ '640e2d42b95d231fbd679408', '640e2d42b95d231fbd679409' ] }
{ _id: ObjectId("640e2f3db95d231fbd679414"),
  order_number: 2,
  date: 2023-03-11T00:00:00.000Z,
  total_sum: 425.45,
  customer: 
   { name: 'Yaroslav',
     surname: 'Borodaienko',
     phones: [ 9876543, 1234567 ],
     address: '02000, Akademika Yanhelya St, 18-20, Kyiv, Ukraine, 02000' },
  payment: { card_owner: 'Yaroslav Borodaienko', cardId: 12345678 },
  items_id: 
   [ '640e2d42b95d231fbd679409',
     '640e2d42b95d231fbd67940c',
     '640e2d42b95d231fbd67940d' ] }
```

### 5 Знайдіть всі замовлення з певним товаром (товарами) (шукати можна по ObjectId)
```
db.orders.find({ items_id: "640e2d42b95d231fbd679409" })
```
```
{ _id: ObjectId("640e2f3db95d231fbd679413"),
  order_number: 1,
  date: 2023-03-10T00:00:00.000Z,
  total_sum: 123.4,
  customer: 
   { name: 'Yaroslav',
     surname: 'Borodaienko',
     phones: [ 9876543, 1234567 ],
     address: '02000, Akademika Yanhelya St, 18-20, Kyiv, Ukraine, 02000' },
  payment: { card_owner: 'Yaroslav Borodaienko', cardId: 12345678 },
  items_id: [ '640e2d42b95d231fbd679408', '640e2d42b95d231fbd679409' ] }
{ _id: ObjectId("640e2f3db95d231fbd679414"),
  order_number: 2,
  date: 2023-03-11T00:00:00.000Z,
  total_sum: 425.45,
  customer: 
   { name: 'Yaroslav',
     surname: 'Borodaienko',
     phones: [ 9876543, 1234567 ],
     address: '02000, Akademika Yanhelya St, 18-20, Kyiv, Ukraine, 02000' },
  payment: { card_owner: 'Yaroslav Borodaienko', cardId: 12345678 },
  items_id: 
   [ '640e2d42b95d231fbd679409',
     '640e2d42b95d231fbd67940c',
     '640e2d42b95d231fbd67940d' ] }
```

### 6 Додайте в усі замовлення з певним товаром ще один товар і збільште існуючу вартість замовлення на деяке значення Х
```
db.orders.updateMany({ items_id: "640e2d42b95d231fbd679409" }, { $inc: { total_sum: 100  }, $push: { items_id: '640e2d42b95d231fbd67940d' }})
```
```
{ acknowledged: true,
  insertedId: null,
  matchedCount: 2,
  modifiedCount: 2,
  upsertedCount: 0 }
```

### 7 Виведіть кількість товарів в певному замовленні
```
db.orders.aggregate([
    {$match: {_id: ObjectId('640e2f3db95d231fbd679413')}},
    {$project: { count: { $size: "$items_id" }}}
])
```
```
{ _id: ObjectId("640e2f3db95d231fbd679413"), count: 4 }
```

### 8 Виведіть тільки інформацію про кастомера і номери кредитної карт, для замовлень вартість яких перевищує певну суму
```
db.orders.find({ total_sum: { $gte: 600 } }, { customer: 1, "payment.cardId": 1 })
```
```
{ _id: ObjectId("640e2f3db95d231fbd679414"),
  customer: 
   { name: 'Yaroslav',
     surname: 'Borodaienko',
     phones: [ 9876543, 1234567 ],
     address: '02000, Akademika Yanhelya St, 18-20, Kyiv, Ukraine, 02000' },
  payment: { cardId: 12345678 } }
```

### 9 Видаліть товар з замовлень, зроблених за певний період дат
```
db.orders.deleteMany({ date: { $gt: ISODate("2023-03-10"), $lt: ISODate("2023-03-12") } })
```
```
{ acknowledged: true, deletedCount: 1 }
```
```
db.orders.count()
```
```
2
```

### 10 Перейменуйте у всіх замовлення ім'я (прізвище) замовника
```
db.orders.updateMany({}, { $set: { "customer.name": "name" } })
```
```
{ acknowledged: true,
  insertedId: null,
  matchedCount: 2,
  modifiedCount: 2,
  upsertedCount: 0 }
```

### 11 Знайдіть замовлення зроблені одним замовником, і виведіть тільки інформацію про кастомера та товари у замовлені підставивши замість ObjectId("***") назви товарів та їх вартість (аналог join-а між таблицями orders та items).
```
db.orders.aggregate([
  {"$match": { "customer.surname": 'Parasyk' }},
{ "$addFields": { "items_id": { "$map": { "input": "$items_id", "in": { "$toObjectId": "$$this" } } } } },
{ "$lookup": { from: "products", localField: "items_id", foreignField: "_id", as: "items" } },
{"$project": {"customer":1, "items":1}}
])
```
```
{ _id: ObjectId("640e2f3db95d231fbd679415"),
  customer: 
   { name: 'name',
     surname: 'Parasyk',
     phones: [ 82734985 ],
     address: 'Lviv' },
  items: 
   [ { _id: ObjectId("640e2d42b95d231fbd67940e"),
       category: 'watch',
       model: 'apple watch series 5',
       producer: 'apple',
       price: 400,
       gps: true },
     { _id: ObjectId("640e2d42b95d231fbd67940f"),
       category: 'watch',
       model: 'samsung watch 2',
       producer: 'samsung',
       price: 300,
       gps: true } ] }
```

## 3
```     
db.createCollection( "reviews", { capped: true, size: 5 } )
db.reviews.insertMany([
{rating: 1, user: 'Alex'},
{rating: 2, user: 'Lex'},
{rating: 3, user: 'Andrew'},
{rating: 4, user: 'Drew'},
{rating: 5, user: 'Yaroslav'},
{rating: 1, user: 'Jack'},
])
db.reviews.count()
```
```
5
```