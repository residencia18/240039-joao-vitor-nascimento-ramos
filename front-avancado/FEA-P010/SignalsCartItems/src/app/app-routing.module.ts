import { ItemComponent } from './components/item/item.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CartItemComponent } from './components/cart-item/cart-item.component';
import { ItemListComponent } from './components/item-list/item-list.component';
import { CartComponent } from './components/cart/cart.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: ItemListComponent },
  { path: 'cart', component: CartComponent },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
