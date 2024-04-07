import { Routes } from '@angular/router';
import { NeedsComponent } from './needs/needs.component';
import { LoginViewComponent } from './login-view/login-view.component';
import { FormsModule } from '@angular/forms';
import { UserViewComponent } from './user-view/user-view.component';
import { CartComponent } from './cart/cart.component';
import { authguardGuard } from './authguard.guard';

export const routes: Routes = [
  { path: 'needs', component: NeedsComponent, canActivate: [authguardGuard]},
  { path: 'login', component: LoginViewComponent,
    children: 
    [
      {
        path: 'user-view', component: UserViewComponent
      },
      {
        path: 'needs', component: NeedsComponent
      }
    ]
  },
  {
    path: 'user-view', component: UserViewComponent, canActivate: [authguardGuard]
  },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'user-view/checkout', component: CartComponent },
];
