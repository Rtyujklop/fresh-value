import { Routes } from '@angular/router';
import { NeedsComponent } from './needs/needs.component';
import { UserLoginComponent } from './user-login/user-login.component';

export const routes: Routes = [
    {
        path:'needs',
        component: NeedsComponent,
        title: 'Needs Page'
    },
    {
        path:'',
        component: UserLoginComponent,
        title: 'Login'
    }
];

