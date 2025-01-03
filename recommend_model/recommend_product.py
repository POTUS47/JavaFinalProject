import torch

from extract_feature import calculate_similarity
from json_about import load_user_features, load_product_features
from similar_product import find_similar_products


def make_recommendation(user_id,result_num=8,file_name="features/user_feature.json"):
    try:
        users_data = load_user_features(file_name)
        user_data = users_data.get(str(user_id))
        if (user_data is None):
            return []
        user_vector = torch.tensor(user_data["user_vector"])
        # 加载商品库中的图像特征
        product_features = load_product_features("features/product_text_feature.json")
        recommendations = find_similar_products(user_vector, product_features, result_num)
        return recommendations
    except Exception as e:
        print("make"+str(e))


