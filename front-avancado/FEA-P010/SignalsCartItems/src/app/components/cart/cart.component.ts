import { Component, Input, computed } from '@angular/core';
import { CartService } from '../../services/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css',
})
export class CartComponent {
  constructor(public service: CartService) {}

  total = computed(() =>
    this.service
      .items()
      .reduce((total, item) => total + item.price * item.quantity, 0)
  );
  quantity = computed(() =>
    this.service.items().reduce((total, item) => total + item.quantity, 0)
  );

}
