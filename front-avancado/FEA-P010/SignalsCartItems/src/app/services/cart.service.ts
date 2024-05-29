import { Injectable, signal } from '@angular/core';
import { CartItem } from '../interfaces/Cart-Item';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  items = signal<CartItem[]>([]);

  constructor() {
    const cart = localStorage.getItem('cart');
    if (cart) {
      this.items.set(JSON.parse(cart));
    }
  }

  add(item: CartItem) {
    if (this.items().find((i) => i.id === item.id)) {
      return;
    }
    this.items.update((items) => [...items, item]);
    this.save();
  }

  remove(itemId: number) {
    this.items.update((items) => items.filter((item) => item.id !== itemId));
    this.save();
  }

  update(cartItem: CartItem) {
    let _item = this.items().find((item) => item.id === cartItem.id)!;
    _item.quantity = cartItem.quantity;

    let _list = this.items().filter((item) => {
      if (item.id === cartItem.id) {
        return _item;
      }
      return item;
    });

    this.items.set(_list);
    this.save();
  }

  save() {
    localStorage.setItem('cart', JSON.stringify(this.items()));
  }
}
