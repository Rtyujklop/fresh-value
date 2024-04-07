import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';

export const authguardGuard: CanActivateFn = (route, state) => {

  const router = inject(Router);

  const localData = localStorage.getItem('token');
  if(localData != null)
  {
    return true;
  }
  else 
  {
    router.navigate(['/login']);
    return false;
  }
};
