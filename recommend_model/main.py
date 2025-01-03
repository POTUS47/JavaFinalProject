from flask import Flask, request, jsonify

from generate_and_update_vector import generate_product_vector, update_user_features, update_product_vector, \
    generate_product_image_vector, update_product_image_vector
from recommend_product import make_recommendation
from similar_product import search_product_by_image

app = Flask(__name__)


@app.route('/', methods=['GET'])
def home():
    return jsonify({"message": "Welcome to the Flask API!"}), 200


# 生成商品特征向量的接口
@app.route('/generate_product_feature', methods=['POST'])
def generate_item_vector():
    try:
        data = request.json
        product_name = data.get('name')
        product_description = data.get('description')
        product_id = data.get('id')
        #image_path = data.get('imagePath', None)  # 如果没有该参数，默认为 None
        # 将 name 和 description 放入列表
        descriptions = [product_name, product_description]
        if generate_product_vector(product_id, descriptions):
            return jsonify({"message": "商品特征向量成功保存！", "product_id": product_id}), 200
        return jsonify({"message": "商品特征向量保存出错！", "product_id": product_id}), 500
    except Exception as e:
        return jsonify({"error": str(e)}), 500


# 更新商品特征向量的接口
@app.route('/update_product_feature', methods=['POST'])
def update_item_vector():
    try:
        data = request.json
        product_name = data.get('name')
        product_description = data.get('description')
        product_id = data.get('id')
        # 将 name 和 description 放入列表
        descriptions = [product_name, product_description]

        update_product_vector(product_id, descriptions)
        return jsonify({"message": "商品特征向量保存成功！", "product_id": product_id}), 200
    except Exception as e:
        return jsonify({"error": str(e)}), 500


# 生成商品图片特征向量的接口
@app.route('/generate_product_img_feature', methods=['POST'])
def generate_item_img_vector():
    try:
        data = request.json
        image_path = data.get('imagePath')
        product_id = data.get('id')
        # 将 name 和 description 放入列表
        if generate_product_image_vector(product_id, image_path):
            return jsonify({"message": "商品图片特征向量成功保存！", "product_id": product_id}), 200
        return jsonify({"message": "商品图片特征向量保存出错！", "product_id": product_id}), 500
    except Exception as e:
        return jsonify({"error": str(e)}), 500

# 更新商品图片特征向量的接口
@app.route('/update_product_feature', methods=['POST'])
def update_item_img_vector():
    try:
        data = request.json
        product_id = data.get('id')
        image_path = data.get('imagePath')
        # 将 name 和 description 放入列表
        update_product_image_vector(product_id, image_path)
        return jsonify({"message": "商品图片特征向量保存成功！", "product_id": product_id}), 200
    except Exception as e:
        return jsonify({"error": str(e)}), 500



# 识图寻找相似商品
@app.route('/find_similar_products', methods=['POST'])
def find_similar_products():
    try:
        data = request.json
        image_path = data.get('imagePath')  # 图片是本地图片路径
        result_num = data.get('resultNum', 10)  # 默认返回前 10 个推荐商品
        if not image_path:
            return jsonify({"error": "Missing imagePath parameter"}), 400
        # 将 name 和 description 放入列表
        recommendations = search_product_by_image(image_path, result_num)
        return jsonify({"recommendations": recommendations}), 200
    except Exception as e:
        return jsonify({"error": str(e)}), 500


# 生成用户向量
@app.route('/update_user_features', methods=['POST'])
def update_user():
    try:
        data = request.json
        user_id = data.get('user_id')
        strings = data.get('strings')  # 用户行为相关的字符串列表

        if not user_id or not strings:
            return jsonify({"error": "Missing required fields"}), 400

        # 更新用户特征
        update_user_features(user_id, strings)

        return jsonify({"message": "User features updated"}), 200

    except Exception as e:
        return jsonify({"error": str(e)}), 500


# 为ID为XX的用户推荐商品
@app.route('/recommend_for_user', methods=['POST'])
def recommend_for_user():
    try:
        data = request.json
        user_id = data.get('user_id')

        print("成功获取"+user_id)

        if not user_id:
            return jsonify({"error": "Missing required fields"}), 400

        recommendations = make_recommendation(user_id)
        print(recommendations)
        return jsonify({"recommendations": recommendations}), 200

    except Exception as e:
        print("start"+str(e))
        return jsonify({"error": str(e)}), 500


@app.route('/test', methods=['POST'])
def testAPI():
    try:
        data = request.json
        user_id = data.get('user_id')
        print("成功进入函数！" + str(user_id))
        return jsonify({"message": "User features updated"}), 200

    except Exception as e:
        return jsonify({"error": str(e)}), 500


if __name__ == '__main__':
    # 在本地启动服务，端口号5000
    print("Starting Flask server...")
    app.run(host='127.0.0.1', port=5000, debug=True)
    # app.run(host='0.0.0.0', port=5000, debug=True)


