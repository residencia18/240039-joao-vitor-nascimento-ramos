import { Injectable } from '@angular/core';
import { CartService } from './cart.service';
import { Item } from '../interfaces/Item';
import { CartItem } from '../interfaces/Cart-Item';

@Injectable({
  providedIn: 'root',
})
export class ItemService {
  public readonly items = [
    {
      id: 1,
      name: 'JavaScript: The Definitive Guide Paperback – January 1, 2012',
      inStock: true,
      price: 42.34,
      imageURL: 'https://m.media-amazon.com/images/I/91z1xY4ppaL._SL1500_.jpg',
    },
    {
      id: 2,
      name: 'HTML5: Up and Running: Dive Into the Future of Web Development Paperback – Illustrated, August 25, 2010',
      inStock: true,
      price: 28.57,
      imageURL:
        'https://m.media-amazon.com/images/I/51bHk82Jn1L._SY445_SX342_.jpg',
    },
    {
      id: 3,
      name: 'CSS Refactoring: Organize Your Style Sheets Successfully Paperback – January 24, 2017',
      inStock: true,
      price: 9.72,
      imageURL: 'https://m.media-amazon.com/images/I/719HYrI9+HL._SY466_.jpg',
    },
    {
      id: 4,
      name: 'Developing with AngularJS: Increasing Productivity with Structured Web Applications" Paperback – October 29, 2014',
      inStock: true,
      price: 37.74,
      imageURL: 'https://m.media-amazon.com/images/I/818TEY-xAUL._SY466_.jpg',
    },
    {
      id: 5,
      name: 'Spring Boot: Accelerate Microservices Development" (Kindle eBook)',
      inStock: true,
      price: 8.98,
      imageURL:
        'https://m.media-amazon.com/images/I/41nkQDdTh5L._SY445_SX342_.jpg',
    },
    {
      id: 6,
      name: 'Design Patterns: Reusable Object-Oriented Software Solutions" (Paperback) - January 1, 2000',
      inStock: true,
      price: 25.38,
      imageURL: 'https://m.media-amazon.com/images/I/81RXMnEXrdL._SY466_.jpg',
    },
    {
      id: 7,
      name: 'Head First Java: Java" (Paperback) - November 16, 2007',
      inStock: true,
      price: 25.56,
      imageURL: 'https://m.media-amazon.com/images/I/61jzaHuWFLL._SY466_.jpg',
    },
  ];

  constructor(private cartService: CartService) {}

  public addToCart(item: Item) {
    const _item: CartItem = { ...item, quantity: 1, gift: false };
    this.cartService.add(_item);
  }
}
