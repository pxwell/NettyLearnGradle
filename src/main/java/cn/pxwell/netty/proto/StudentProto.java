// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Student.proto

package cn.pxwell.netty.proto;

public final class StudentProto {
  private StudentProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_cn_pxwell_netty_proto_MyRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_cn_pxwell_netty_proto_MyRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_cn_pxwell_netty_proto_MyResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_cn_pxwell_netty_proto_MyResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\rStudent.proto\022\025cn.pxwell.netty.proto\"\035" +
      "\n\tMyRequest\022\020\n\010username\030\001 \001(\t\"\036\n\nMyRespo" +
      "nse\022\020\n\010realname\030\001 \001(\t2p\n\016StudentService\022" +
      "^\n\025getRealNameByUsername\022 .cn.pxwell.net" +
      "ty.proto.MyRequest\032!.cn.pxwell.netty.pro" +
      "to.MyResponse\"\000B\'\n\025cn.pxwell.netty.proto" +
      "B\014StudentProtoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_cn_pxwell_netty_proto_MyRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_cn_pxwell_netty_proto_MyRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_cn_pxwell_netty_proto_MyRequest_descriptor,
        new String[] { "Username", });
    internal_static_cn_pxwell_netty_proto_MyResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_cn_pxwell_netty_proto_MyResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_cn_pxwell_netty_proto_MyResponse_descriptor,
        new String[] { "Realname", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}