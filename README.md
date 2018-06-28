# interlok-config-conditional [![GitHub tag](https://img.shields.io/github/tag/adaptris/interlok-config-conditional.svg)](https://github.com/adaptris/interlok-config-conditional/tags) ![license](https://img.shields.io/github/license/adaptris/interlok-config-conditional.svg) [![Build Status](https://travis-ci.org/adaptris/interlok-config-conditional.svg?branch=develop)](https://travis-ci.org/adaptris/interlok-config-conditional) [![codecov](https://codecov.io/gh/adaptris/interlok-config-conditional/branch/develop/graph/badge.svg)](https://codecov.io/gh/adaptris/interlok-config-conditional)

Because you know you want to do this; though that's a lot of nested conditions.

```
<if-then-otherwise>
   <unique-id>4550829f-69ea-4b59-bc45-5a8ff35c541e</unique-id>
   <condition class="and">
    <condition class="metadata">
     <metadata-key>key1</metadata-key>
     <operator class="not-null"/>
    </condition>
    <condition class="or">
     <condition class="expression">
      <algorithm>(%message{key1} - 10) == %message{key2}</algorithm>
     </condition>
     <condition class="metadata">
      <metadata-key>key2</metadata-key>
      <operator class="equals">
       <value>myValue</value>
      </operator>
     </condition>
    </condition>
   </condition>
   <then>
    <service class="log-message-service">
     <unique-id>5a7b5344-a8ef-4289-a4a4-8dfc3f048b5b</unique-id>
     <log-level>DEBUG</log-level>
    </service>
   </then>
   <otherwise>
    <service class="log-message-service">
     <unique-id>f520b0e6-dcfa-4949-9150-8506621d0bf9</unique-id>
     <log-level>DEBUG</log-level>
    </service>
   </otherwise>
  </if-then-otherwise>
```
