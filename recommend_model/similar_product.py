from extract_feature import extract_image_feature, calculate_similarity
from initialize_model import ModelManager
from json_about import load_product_features


# 基于特征向量计算商品的相似度，并推荐商品
def find_similar_products(target_feature, product_features, result_num=10):
    recommendations = []
    # 对每个商品计算与用户特征向量的相似度
    for product_id, product_feature in product_features.items():
        similarity = calculate_similarity(target_feature, product_feature)
        recommendations.append((product_id, similarity))

    # 按照相似度排序，选择最相关的商品
    recommendations.sort(key=lambda x: x[1], reverse=True)
    return recommendations[:result_num]  # 返回前result_num个推荐的商品


# 输入图片并与商品库中的商品进行相似度对比
def search_product_by_image(image_path, result_num=10):
    # 获取单例的模型实例
    model_manager = ModelManager.get_instance()

    # 提取查询图片的特征
    query_image_feature = extract_image_feature(image_path, model_manager.model,
                                                model_manager.preprocess, model_manager.device)

    # 加载商品库中的图像特征
    product_features = load_product_features("features/product_img_feature.json")

    top_recommendations = find_similar_products(query_image_feature, product_features, result_num)
    return top_recommendations
