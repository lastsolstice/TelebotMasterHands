/* (c) Copyright, Real-Time Innovations, Inc. 2003-2009. All rights reserved. */
/*
 * @(#)reda_inlineList.h    generated by: makeheader    Sat Nov 23 02:38:02 2013
 *
 *		built from:	inlineList.ifc
 */

#ifndef reda_inlineList_h
#define reda_inlineList_h


  #ifndef osapi_type_h
    #include "osapi/osapi_type.h"
  #endif

  #ifndef reda_dll_h
    #include "reda/reda_dll.h"
  #endif

#ifdef __cplusplus
    extern "C" {
#endif

struct REDAInlineList;

struct REDAInlineListNode;

struct REDAInlineListWRNode;

struct REDAInlineListUserDataNode;

extern REDADllExport struct REDAInlineListNode *
REDAInlineList_getFirst(const struct REDAInlineList *me);

extern REDADllExport struct REDAInlineListNode *
REDAInlineList_getLast(const struct REDAInlineList *me);

extern REDADllExport
RTIBool REDAInlineList_isNodeInList(struct REDAInlineList *me,
				     struct REDAInlineListNode *node);

extern REDADllExport
void REDAInlineListNode_init(struct REDAInlineListNode *me);

extern REDADllExport
void REDAInlineList_init(struct REDAInlineList *me);

extern REDADllExport
void REDAInlineList_addNodeToFrontEA(struct REDAInlineList *me,
				     struct REDAInlineListNode *node);

extern REDADllExport
void REDAInlineList_assertNodeToFrontEA(struct REDAInlineList *me,
                                        struct REDAInlineListNode *node);

extern REDADllExport
void REDAInlineList_addNodeAfterEA(struct REDAInlineList *me,
				   struct REDAInlineListNode *existingNode,
				   struct REDAInlineListNode *node);

extern REDADllExport
void REDAInlineList_addNodeBeforeEA(struct REDAInlineList *me,
				   struct REDAInlineListNode *existingNode,
				   struct REDAInlineListNode *node);

extern REDADllExport
void REDAInlineList_addNodeToBackEA(struct REDAInlineList *me,
				     struct REDAInlineListNode *node);

extern REDADllExport
void REDAInlineList_assertNodeToBackEA(struct REDAInlineList *me,
                                       struct REDAInlineListNode *node);

extern REDADllExport
void REDAInlineList_removeNodeEA(struct REDAInlineList *me,
				 struct REDAInlineListNode *node);

extern REDADllExport
int REDAInlineList_getSize(const struct REDAInlineList *me);

extern REDADllExport 
void REDAInlineList_setUserData(
    struct REDAInlineList *me, void * userData);

extern REDADllExport 
void * REDAInlineList_getUserData(
    struct REDAInlineList *me);


#ifdef __cplusplus
    }	/* extern "C" */
#endif

/* pick up hidden performance boosting macros and optimizations */
  #include "reda/reda_inlineList_impl.h"

#endif /* reda_inlineList_h */
