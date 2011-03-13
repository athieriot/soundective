package org.soundective.utils.serializer

import com.google.gson._

/**
 * Utility object that allows seamless usage of Gson with Scala (collections and eventually more)
 *
 * Implementation of the solution proposed by Dirk at
 * http://groups.google.com/group/play-framework/browse_thread/thread/5639a3d3c15dbd51/94fe65acc5ba93b6#94fe65acc5ba93b6
 *
 * @author Manuel Bernhardt <bernhardt.manuel@gmail.com>
 */

object ScalaGsonSerializer {

  val builder = new GsonBuilder()
  builder.registerTypeAdapter(classOf[scala.collection.immutable.$colon$colon[_]], new CollectionSerializer)
  val gson = builder.create

  def toJson(obj: Any) = gson.toJson(obj)

}

class CollectionSerializer extends
JsonSerializer[scala.collection.immutable.$colon$colon[_]] {
  override def serialize(items: scala.collection.immutable.$colon$colon[_],
                         objType: java.lang.reflect.Type,
                         context: JsonSerializationContext): JsonElement = {
    val json = new JsonArray()
    items.foreach(item => json.add(context.serialize(item)))
    return json
  }
}