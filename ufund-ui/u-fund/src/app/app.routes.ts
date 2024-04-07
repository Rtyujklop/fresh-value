import { Routes } from '@angular/router';
import { NeedsComponent } from './needs/needs.component';
import { LoginViewComponent } from './login-view/login-view.component';
import { UserViewComponent } from './user-view/user-view.component';
import { CartComponent } from './cart/cart.component';

export const routes: Routes = [
  { path: 'needs', component: NeedsComponent },
  { path: 'login', component: LoginViewComponent },
  { path: 'user-view', component: UserViewComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'user-view/checkout', component: CartComponent },
];
