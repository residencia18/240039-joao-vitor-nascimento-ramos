import { Component, Input } from '@angular/core';
import { Item } from '../../interfaces/Item';
import { Router } from '@angular/router';
import { ItemService } from '../../services/item.service';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrl: './item.component.css',
})
export class ItemComponent {
  @Input() item: Item = {} as Item;

  constructor(public service: ItemService, private router: Router) {}

  public addToCart() {
    this.service.addToCart(this.item);
    this.router.navigate(['/cart']);
  }
}
