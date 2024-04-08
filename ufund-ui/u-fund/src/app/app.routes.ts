import { Routes } from '@angular/router';
import { NeedsComponent } from './needs/needs.component';
import { LoginViewComponent } from './login-view/login-view.component';
import { FormsModule } from '@angular/forms';
import { UserViewComponent } from './user-view/user-view.component';
import { CartComponent } from './cart/cart.component';
import { userGuard } from './authguard/user.guard';
import { adminGuard } from './authguard/admin.guard';
import { checkoutGuard } from './authguard/checkout.guard';

export const routes: Routes = [
  { path: 'needs', component: NeedsComponent, canActivate: [adminGuard] },
  {
    path: 'login',
    component: LoginViewComponent,
    children: [
      {
        path: 'user-view',
        component: UserViewComponent,
      },
      {
        path: 'needs',
        component: NeedsComponent,
      },
      { path: 'user-view/checkout', component: CartComponent },
    ],
  },
  {
    path: 'user-view',
    component: UserViewComponent,
    canActivate: [userGuard],
  },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  {
    path: 'user-view/checkout',
    component: CartComponent,
    canActivate: [checkoutGuard],
  },
];
