/* (c) Copyright, Real-Time Innovations, Inc. 2003-2009. All rights reserved. */
/*
 * @(#)osapi_sharedMemorySegment_impl.h    generated by: makeheader    Sat Nov 23 02:37:47 2013
 *
 *		built from:	sharedMemorySegment_impl.ifc
 */

#ifndef osapi_sharedMemorySegment_impl_h
#define osapi_sharedMemorySegment_impl_h


#ifdef __cplusplus
    extern "C" {
#endif

struct RTIOsapiSharedMemorySegmentHeader {
    /*i Size requested by the caller of attachOrCreate */
    int size;

    /*i PID of the process that created the segment.
	It is set to the PID when the segment is created, and is set
	to ZERO when the segment is destroyed. In this way it makes
	safe the check of Pid == process_itself in the createOrAttach.
        On Integrity this is not used because Integrity doesn't
	guarantee an unique ID for each process.
     */
    RTI_UINT32 ownerPid;

    /*i Key that identifies this segment */
    int key;

    /*i The total size of the allocated buffer */
    int allocatedSize;
};


struct RTIOsapiSharedMemorySegmentHandleImpl {
    /*i handle to the shared memory.
        Used as a reference when delete the shared memory.
    */
    RTIOsapiSharedMemoryNativeHandle_Segment nativeHandle;

    /*i pointer to the first byte of the shared segment. The segment header
        can be found on top.
    */
    struct RTIOsapiSharedMemorySegmentHeader * ptrHeader;  /* NULL=unattached */

    /*i Pointer to the user's data inside the segment.
        This is equivalent of ptrHeader+sizeof(RTOsapiSharedMemorySegmentHeader)
    */
    void * ptrUserData;
};


struct RTIOsapiSharedMemorySegmentHandle {
    union {
	struct RTIOsapiSharedMemorySegmentHandleImpl handle;
	int pad[8];
    } impl;
};


#define RTIOsapiSharedMemorySegment_getAddress(h) \
    ((h)->impl.handle.ptrUserData)

#define RTIOsapiSharedMemorySegment_getSize(h) \
    ((h)->impl.handle.ptrHeader->size)

#define RTIOsapiSharedMemorySegment_getKey(h) \
    ((h)->impl.handle.ptrHeader->key)

#define RTIOsapiSharedMemorySegment_getOwnerPid(h) \
    ((h)->impl.handle.ptrHeader->ownerPid)



#ifdef __cplusplus
    }	/* extern "C" */
#endif

#endif /* osapi_sharedMemorySegment_impl_h */
