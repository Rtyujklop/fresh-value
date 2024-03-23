import { Routes } from '@angular/router';
import { NeedsComponent } from './needs/needs.component';
import { LoginViewComponent } from './login-view/login-view.component';

export const routes: Routes = 
[
    { path: 'needs', component: NeedsComponent},
    { path: 'login', component: LoginViewComponent},
    { path: '', redirectTo: '/login', pathMatch: 'full'},
];
