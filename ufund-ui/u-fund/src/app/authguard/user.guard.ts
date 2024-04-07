import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';

export const userGuard: CanActivateFn = (route, state) => {
  
  const router = inject(Router);

  const localData = localStorage.getItem('token');
  
  if(localData == 'user-token' || localData == 'admin-token')
  {
    return true;
  }
  else
  {
    alert("You do not have permission to access this page.")
    router.navigate(['/login']);
    return false;
  }
};
