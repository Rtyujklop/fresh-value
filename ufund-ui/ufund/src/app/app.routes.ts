import { Routes } from '@angular/router';
import { NeedsComponent } from './needs/needs.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { UserviewComponent } from './userview/userview.component';

export const routes: Routes = [
    {
        path:'needs',
        component: NeedsComponent,
        title: 'Admin'
    },
    {
        path:'',
        component: UserLoginComponent,
        title: 'Login'
    },
    {
        path:'user',
        component: UserviewComponent,
        title: 'User'
    }
];

