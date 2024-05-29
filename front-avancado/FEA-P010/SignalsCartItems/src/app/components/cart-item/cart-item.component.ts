import { Component, Input } from '@angular/core';
import { CartItem } from '../../interfaces/Cart-Item';
import { CartService } from '../../services/cart.service';


@Component({
  selector: 'app-cart-item',
  templateUrl: './cart-item.component.html',
  styleUrl: './cart-item.component.css',
})
export class CartItemComponent {
  @Input() item: CartItem = {} as CartItem;

  constructor(private cartService: CartService) {}

  remove() {
    this.cartService.remove(this.item.id);
  }

  quantity() {
    if (this.item.quantity <= 0 || this.item.quantity > 999) {
      this.item.quantity = 1;
    }
    this.cartService.update(this.item);
  }
}
