import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';

export const adminGuard: CanActivateFn = (route, state) => {

  const router = inject(Router);

  const localData = localStorage.getItem('token');
  
  if(localData == 'admin-token')
  {
    return true;
  }
  else if (localData == undefined)
  {
    alert("You do not have permission to access this page.")
    router.navigate(['/login']);
    return false;
  }
  else 
  {
    alert("You do not have permission to access this page.")
    router.navigate(['/user-view']);
    return false;
  }
};
