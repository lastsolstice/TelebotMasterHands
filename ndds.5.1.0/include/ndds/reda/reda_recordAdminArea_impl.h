/* (c) Copyright, Real-Time Innovations, Inc. 2003-2009. All rights reserved. */
/*
 * @(#)reda_recordAdminArea_impl.h    generated by: makeheader    Sat Nov 23 02:38:01 2013
 *
 *		built from:	recordAdminArea_impl.ifc
 */

#ifndef reda_recordAdminArea_impl_h
#define reda_recordAdminArea_impl_h


  #ifndef reda_epoch_h
    #include "reda/reda_epoch.h"
  #endif
  #ifndef reda_worker_h
    #include "reda/reda_worker.h"
  #endif
  #ifndef reda_skiplist_h
    #include "reda/reda_skiplist.h"
  #endif

#ifdef __cplusplus
    extern "C" {
#endif

struct REDARecordAdminArea {
    /*i REDA_WEAK_REFERENCE_INDEX_INVALID means there isn't a weak 
      referent for this record in the weak-reference manager's array 
      of weak referents yet.  That is, no reference to this record 
      has been doled out yet.
    */
    int _referentIndex;

    /*i the record-specific EA */
    const struct REDAExclusiveArea *_recordEA;

    /*i Link all the removed records so that we can traverse them efficiently */
    const struct REDASkiplistNode *_nextRemovedSkiplistNode;

    /*i Remember the current cursor modifying the record. A pointer is used
      here just for convenience. It should be treated as a cursor ID, and should
      never be de-referenced.
     */
    void *_cursor;
     
    /*i This is managed
        by REDATableEpoch and SHOULD ONLY BE USED through that 
	module (which is why this has the _te_ prefix)
    */
    REDAEpoch _te_tableEpoch;
    
    /*i This is managed
        by REDAModificationEpoch and SHOULD ONLY BE USED through that 
	module (which is why this has the _me_ prefix)
    */
    REDAEpoch _me_modificationEpoch;
};


#ifdef __cplusplus
    }	/* extern "C" */
#endif

#endif /* reda_recordAdminArea_impl_h */
