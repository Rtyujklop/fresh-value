import { Routes } from '@angular/router';
import { NeedsComponent } from './needs/needs.component';
import { UserLoginComponent } from './user-login/user-login.component';

export const routes: Routes = 
[
    { path: 'needs', component: NeedsComponent},
    { path: 'login', component: UserLoginComponent},
    { path: '', redirectTo: '/login', pathMatch: 'full'},
];
