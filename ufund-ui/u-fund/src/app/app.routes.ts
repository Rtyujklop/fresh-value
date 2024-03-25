import { Routes } from '@angular/router';
import { NeedsComponent } from './needs/needs.component';
import { LoginViewComponent } from './login-view/login-view.component';
import { FormsModule } from '@angular/forms';
import { UserViewComponent } from './user-view/user-view.component';

export const routes: Routes = 
[
    { path: 'needs', component: NeedsComponent},
    { path: 'login', component: LoginViewComponent},
    { path: 'user-view', component: UserViewComponent},
    { path: '', redirectTo: '/login', pathMatch: 'full'},
    
];
