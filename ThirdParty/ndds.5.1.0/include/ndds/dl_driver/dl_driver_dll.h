/* (c) Copyright, Real-Time Innovations, Inc. 2003-2009. All rights reserved. */
/*
 * @(#)dl_driver_dll.h    generated by: makeheader    Sat Nov 23 02:37:53 2013
 *
 *		built from:	dll.ifc
 */

#ifndef dl_driver_dll_h
#define dl_driver_dll_h



#if defined(RTI_WIN32) || defined(RTI_WINCE)
  #if defined(RTI_dl_driver_DLL_EXPORT)
    #define DLDRIVERDllExport __declspec( dllexport )
  #else
    #define DLDRIVERDllExport
  #endif /* RTI_persistence_DLL_EXPORT */

  #if defined(RTI_dl_driver_DLL_VARIABLE) 
    #if defined(RTI_dl_driver_DLL_EXPORT)
      #define DLDRIVERDllVariable __declspec( dllexport )
    #else
      #define DLDRIVERDllVariable __declspec( dllimport )
    #endif /* RTI_persistence_DLL_EXPORT */
  #else 
    #define DLDRIVERDllVariable
  #endif /* RTI_persistence_DLL_VARIABLE */
#else
  #define DLDRIVERDllExport
  #define DLDRIVERDllVariable
#endif /* RTI_WIN32 || RTI_WINCE */


#endif /* dl_driver_dll_h */
