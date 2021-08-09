package GithubAPITest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueComment;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

public class githubTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		System.out.println("test");
		// properties 파일로 token 관리---> token destroy 되면 github server로 연결안되서 새로 생성해서 바꿔줘야함
		String path = "src/main/resources/application.properties";
		//GitHub github = GitHubBuilder.fromPropertyFile(path).build();
		// 아래방법도 가능하긴 하나, 비추
		 GitHub github = new GitHubBuilder().withOAuthToken("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").build();
		github.checkApiUrlValidity();

		// 해당 repository 가져오기
		GHRepository repository = github.getRepository("cmsh0703/javaStudy");

		// 모든 issue 객체 가져오기
		List<GHIssue> issues = repository.getIssues(GHIssueState.ALL);

		//HashMap<String, Integer> hm = new HashMap<String, Integer>();
		HashMap hm = new HashMap();
		
		for(int i= 0; i < issues.size(); i++) {
			System.out.println("["+(i+1)+" 번째 issue] ===== "+issues.get(i));
			//issue별로 comment 가져오기
			List<GHIssueComment> issueComment = issues.get(i).getComments();
			
			for(int j = 0; j < issueComment.size(); j++) {
				GHIssueComment commentTmp = issueComment.get(j);
				String userName = commentTmp.getUserName();
				String content = commentTmp.getBody();
				System.out.print("userName : "+userName+", content : "+content);
				
				if(hm.containsKey(userName)){
					hm.put(userName, Integer.parseInt(hm.get(userName).toString())+1);
					System.out.println("참석률 증가");
				}else {
					hm.put(userName, 1);
					System.out.println("첫댓글");
				}
			}
			System.out.println();
		}
		
		Iterator st = hm.keySet().iterator();
		for(;st.hasNext();) {
			String name = st.next().toString();
			System.out.println("사용자 "+name+"의 참석률 : "+(Integer.parseInt(hm.get(name).toString())/(issues.size()*1.00))*100+" %");
		}
		
	}

}
